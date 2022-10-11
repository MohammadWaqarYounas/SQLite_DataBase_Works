package com.example.databases_implementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databases_implementation.Classes.RecyclerItemClickListener;
import com.example.databases_implementation.ModelClasses.SQLite_Model;
import com.example.databases_implementation.databinding.ActivityMain2Binding;
import com.example.databases_implementation.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity{

    public static ArrayList<SQLite_Model> personlist = new ArrayList<>();;
    private SQLite_DB db = new SQLite_DB(MainActivity2.this,"PERSONDB",null,1);
    private SQLite_Adaptor adaptor;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        personlist = new Gson().fromJson("test", (Type) SQLite_Model.class);
        

        personlist = db.readdata();
        adaptor= new SQLite_Adaptor(personlist,MainActivity2.this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(adaptor);


        binding.recycler.addOnItemTouchListener( new RecyclerItemClickListener(this, binding.recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i= new Intent(MainActivity2.this,MainActivity3.class);
                i.putExtra("userdata",personlist.get(position));
                startActivity(i);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(MainActivity2.this, "I am  ID: "+(position+1), Toast.LENGTH_SHORT).show();
            }
        }));

    }

}