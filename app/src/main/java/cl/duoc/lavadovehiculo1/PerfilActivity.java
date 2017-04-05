package cl.duoc.lavadovehiculo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilActivity extends AppCompatActivity  implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView lblEmail;
    private Button btnCerrarSession;

    private DatabaseReference databaseReference;


    private Button btnGuardaInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginFinalActivity.class));

        }

        databaseReference = FirebaseDatabase.getInstance().getReference();



        btnGuardaInfo = (Button)findViewById(R.id.btnGuardaInfo);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        lblEmail = (TextView)findViewById(R.id.lblEmail);

        lblEmail.setText("Bienvenido "+ user.getEmail());

        btnCerrarSession = (Button)findViewById(R.id.btnCerrarSession);
        btnCerrarSession.setOnClickListener(this);

        btnGuardaInfo.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if(view == btnCerrarSession){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginFinalActivity.class));


        }



    }
}
