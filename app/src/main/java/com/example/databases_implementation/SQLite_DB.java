package com.example.databases_implementation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.databases_implementation.ModelClasses.SQLite_Model;

import java.util.ArrayList;

public class SQLite_DB extends SQLiteOpenHelper{
    SQLiteDatabase db;

    public SQLite_DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "First_Name" + " TEXT,"
                + "Last_Name"+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }
    public void insert_person(String fname,String lname){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First_Name",fname);
        values.put("Last_Name",lname);
        db.insert("person",null,values);
        db.close();
    }
    public void delete_data(String id){
        db = this.getWritableDatabase();
        db.delete("person","ID='"+id+"'",null);
        db.close();
    }


    public ArrayList<SQLite_Model> readdata() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM person", null);
        ArrayList<SQLite_Model> ModalArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ModalArrayList.add(new SQLite_Model(cursor.getString(0)
                        , cursor.getString(1)
                        , cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ModalArrayList;
    }

    public void update(String fname, String lname, String id){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First_Name",fname);
        values.put("Last_Name",lname);
        db.update("person", values, "ID='"+id+"'", new String[]{});
        db.close();

    }

//Simple VIEW DATA.......................
    public void view_data(TextView textView){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM person", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
        cursor.close();
        db.close();
    }

}
