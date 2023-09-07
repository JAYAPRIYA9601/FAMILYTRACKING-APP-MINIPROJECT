package com.example.firstpage;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Mainscreen extends AppCompatActivity {
    ArrayList<String> listitems=new ArrayList<>();
    ArrayAdapter<String> adapter;
  @Override
    protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_mainscreen);

      EditText input=findViewById(R.id.et_input);
      Button Save=findViewById(R.id.bt_save);
      ListView listView=findViewById(R.id.listView);

      adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitems);
      listView.setAdapter(adapter);


      Save.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              listitems.add(input.getText().toString());
              adapter.notifyDataSetChanged();
              input.setText("");
          }
      });
  }

}