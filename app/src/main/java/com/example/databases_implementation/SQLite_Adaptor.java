package com.example.databases_implementation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.databases_implementation.ModelClasses.SQLite_Model;

import java.util.ArrayList;

public class SQLite_Adaptor extends RecyclerView.Adapter<SQLite_Adaptor.ViewHolder>  {
    private ArrayList<SQLite_Model> modelArrayList;
    private Context context;

    public SQLite_Adaptor(ArrayList<SQLite_Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SQLite_Model modal = modelArrayList.get(position);
        holder.name.setText(modal.getFirstname()+" "+modal.getLastname());
        holder.id.setText(modal.getId());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.PersonName);
            id = itemView.findViewById(R.id.PersonID);
        }

    }
}
