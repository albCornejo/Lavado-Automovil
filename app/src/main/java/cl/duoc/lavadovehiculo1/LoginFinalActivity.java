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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFinalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin1;
    private EditText txtEmail, txtPassword;
    private TextView lblRegistrar;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_final);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogin1 = (Button) findViewById(R.id.btnLogin1);
        lblRegistrar = (TextView) findViewById(R.id.lblRegistrar);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

        }

        progressDialog = new ProgressDialog(this);

        btnLogin1.setOnClickListener(this);
        lblRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnLogin1){

            usuarioLogin();
        }

        if (view.getId() == R.id.lblRegistrar){


                redirecionRegistro();

        }

    }

    private void redirecionRegistro() {
        finish();
        startActivity(new Intent(this, RegistroActivity.class));

    }

    private void usuarioLogin() {

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

        progressDialog.setMessage("Validando Datos...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                        }else{
                            Toast.makeText(LoginFinalActivity.this,"Email o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
