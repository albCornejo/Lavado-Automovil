package cl.duoc.lavadovehiculo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistro1;
    private EditText txtEmail, txtPassword;
//    private TextView lblLogin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

        }

        progressDialog = new ProgressDialog(this);

        btnRegistro1 = (Button)findViewById(R.id.btnRegistro1);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
//        lblLogin = (TextView)findViewById(R.id.lblRegistrar);

        btnRegistro1.setOnClickListener(this);
//        lblLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == btnRegistro1){

            registrarUsuario();
        }

/*        if (view == lblLogin)
        {
            startActivity(new Intent(this, LoginFinalActivity.class));
        }*/
    }

    private void registrarUsuario() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Porfavor ingrese un Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() <=5)
        {
            Toast.makeText(this, "Porfavor ingrese una contraseña mas larga", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){

                                finish();
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                            Toast.makeText(RegistroActivity.this,"Registro Exitoso", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistroActivity.this,"Registro Erroneo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
