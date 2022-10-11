package com.example.databases_implementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databases_implementation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    SQLite_DB dblite;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dblite= new SQLite_DB(MainActivity.this,"PERSONDB",null,1);


        binding.ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
        binding.InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dblite.insert_person( binding.FirstName.getText().toString(),binding.LastName.getText().toString());
                    Toast.makeText(MainActivity.this, "ADDED: "+" "+binding.FirstName.getText().toString()+" "+binding.LastName.getText().toString(), Toast.LENGTH_SHORT).show();
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}