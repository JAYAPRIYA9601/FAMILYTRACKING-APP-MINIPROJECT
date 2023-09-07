package com.example.firstpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    TextView btn;

    private EditText inputUsername,inputPassword,inputConformPassword,inputEmail;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn=findViewById(R.id.alreadyHaveAccount);
        inputUsername=findViewById(R.id.inputUsername);
        inputPassword=findViewById(R.id.inputPassword);
        inputConformPassword=findViewById(R.id.inputConformPassword);
        inputEmail=findViewById(R.id.inputEmail);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(register.this);

        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, MainActivity.class));
            }
        });
    }

    private void checkCrededentials() {
        String username=inputUsername.getText().toString();
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String conformPassword=inputConformPassword.getText().toString();

        if(username.isEmpty() || username.length()<7)
        {
            showError(inputUsername,"your username is not valid!");
        }
        else if(email.isEmpty())
        {
            showError(inputEmail,"Email is not valid");
        }
        else if(password.isEmpty() || password.length()<7)
        {
            showError(inputPassword,"Password must be 7 characters");
        }
        else if(conformPassword.isEmpty() || !conformPassword.equals(password))
        {
            showError(inputConformPassword,"Password not match!");
        }
        else
        {
            mLoadingBar.setTitle("Registeration");
            mLoadingBar.setMessage("Please wait,While check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(register.this,"Successfully Registered",Toast.LENGTH_SHORT).show();

                  Intent intent=new Intent(register.this,MainActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent);
                        mLoadingBar.dismiss();
                    }
                    else{
                        Toast.makeText(register.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}