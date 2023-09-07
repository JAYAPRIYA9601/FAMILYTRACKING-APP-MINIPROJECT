package com.example.firstpage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    TextView btn;
    EditText inputEmail,inputPassword;
    Button btnlogin;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.textViewSignUp);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        btnlogin=findViewById(R.id.btnlogin);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(MainActivity.this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
    }

    private void checkCrededentials() {

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();


        if (email.isEmpty()) {
            showError(inputEmail, "Email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be 7 characters");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait,While check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(MainActivity.this, home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        mLoadingBar.dismiss();
                    }
                }
            });
        }
    }
        private void showError(EditText input,String s)
        {
            input.setError(s);
            input.requestFocus();

        }
    }



