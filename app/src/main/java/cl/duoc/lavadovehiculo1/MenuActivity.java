package cl.duoc.lavadovehiculo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAgregarLavado, btnModificarPrecios, btnListarLavados;

    private TextView lblEmail;
    private TextView lblCerrarSesion;

    private FirebaseAuth firebaseAuth;
   // private TextView txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginFinalActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        lblEmail = (TextView)findViewById(R.id.lblEmail);

        lblEmail.setText("Bienvenido: "+ user.getEmail());

        lblCerrarSesion = (TextView)findViewById(R.id.lblCerrarSesion);
        lblCerrarSesion.setOnClickListener(this);



        btnAgregarLavado = (Button)findViewById(R.id.btnAgregarLavado);
        btnModificarPrecios = (Button)findViewById(R.id.btnModificarPrecios);
        btnListarLavados = (Button)findViewById(R.id.btnListarLavados);

        btnAgregarLavado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), IngresoLavadoActivity.class);
                startActivity(i);
            }
        });

        btnModificarPrecios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ModiPreciosActivity.class);
                startActivity(i);
            }
        });

        btnListarLavados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), listarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == lblCerrarSesion){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginFinalActivity.class));


        }

    }
}
