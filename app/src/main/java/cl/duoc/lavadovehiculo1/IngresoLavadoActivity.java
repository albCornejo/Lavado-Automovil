package cl.duoc.lavadovehiculo1;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class IngresoLavadoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPresupuesto;

    private Spinner spnTipoVehiculo;
    private EditText txtPatente, txtValorLavado;

    private Calendar calendar;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_lavado);



        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginFinalActivity.class));

        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


        btnPresupuesto = (Button) findViewById(R.id.btnPresupuesto);

        spnTipoVehiculo = (Spinner) findViewById(R.id.spnTipoVehiculo);
        txtPatente = (EditText) findViewById(R.id.txtPatente);
        txtValorLavado = (EditText) findViewById(R.id.txtValorLavado);


        FirebaseUser user = firebaseAuth.getCurrentUser();

        btnPresupuesto.setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void GuardarLavado() {


        String tipoV = spnTipoVehiculo.getSelectedItem().toString();
        String patente = txtPatente.getText().toString().trim();
        String precio = txtValorLavado.getText().toString().trim();
        String fecha = dateToString();


        if (TextUtils.isEmpty(patente))
        {
            Toast.makeText(this, "Porfavor ingrese Patente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(precio))
        {
            Toast.makeText(this, "Porfavor ingrese Valor del Lavado", Toast.LENGTH_SHORT).show();
            return;
        }


        Lavado lavado1 = new Lavado(tipoV, patente, precio, fecha);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        spnTipoVehiculo.setSelection(0);
        txtPatente.setText("");
        txtValorLavado.setText("");

/*        DatabaseReference postsRef =  databaseReference.child("lavaAuto");

        DatabaseReference newPostRef = postsRef.push();

        newPostRef.setValue(new Lavado(tipoV, patente, precio, fecha));*/


        /*databaseReference.push().setValue(lavado1);*/

        databaseReference.child("lavaAuto").push().setValue(lavado1);

/*        databaseReference.child("lavaAuto").push().setValue(new Lavado(tipoV, patente, precio, fecha));*/



      /*  newPostRef.setValue(new Lavado(tipoV, patente, precio, fecha));*/

        /*databaseReference.child(user.getUid()).setValue(lavado1);*/



        Toast.makeText(this, "Info Guardada...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        if (view == btnPresupuesto) {
//            startActivity(new Intent(this, DetallePresupuestoActivity.class));
            GuardarLavado();
        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("IngresoLavado Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();


        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public static String dateToString(){
        String date = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int mes = calendar.get(java.util.Calendar.MONTH);
        mes++;
        date = formatDate(calendar.get(java.util.Calendar.DAY_OF_MONTH))+"/"+
                formatDate(mes)+"/"+
                formatDate(calendar.get(java.util.Calendar.YEAR));


        return date;
    }

    public static String hourToString(){
        String hour = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        hour = formatDate(calendar.get(java.util.Calendar.HOUR_OF_DAY))+":"+
                formatDate(calendar.get(java.util.Calendar.MINUTE));


        return hour;
    }

    private static String formatDate(int value){
        return (value>9?""+value:"0"+value);
    }

}
