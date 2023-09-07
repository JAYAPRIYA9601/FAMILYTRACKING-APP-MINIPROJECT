package com.example.firstpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class home extends AppCompatActivity {
    private Button join,create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        join=findViewById(R.id.join);
        create=findViewById(R.id.create);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(home.this,joingp.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(home.this,creategp.class);
                startActivity(intent);
            }
        });



    }
}