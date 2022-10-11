package com.example.databases_implementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.databases_implementation.ModelClasses.SQLite_Model;
import com.example.databases_implementation.databinding.ActivityMain2Binding;
import com.example.databases_implementation.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    SQLite_DB db;
    SQLite_Model model;
    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        db = new SQLite_DB(MainActivity3.this,"PERSONDB",null,1);
        model = new SQLite_Model();
        model = (SQLite_Model) getIntent().getSerializableExtra("userdata");


        binding.PersonID.setText(model.getId());
        binding.PersonName.setText(model.getFirstname()+" "+model.getLastname());


        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete_data(binding.PersonID.getText().toString());
                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                finish();
                startActivity(i);
            }
        });
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.llEditing.setVisibility(View.VISIBLE);
                binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.update(binding.newFname.getText().toString(),binding.newLname.getText().toString(),binding.PersonID.getText().toString());
                        binding.llEditing.setVisibility(View.GONE);
                        finish();
                        Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                        startActivity(i);
                    }
                });
            }
        });

    }
}