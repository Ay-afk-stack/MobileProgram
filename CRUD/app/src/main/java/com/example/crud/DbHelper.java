package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Students (rollno TEXT primary key,name TEXT ,studentClass Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Students");
        onCreate(db);
    }

    public void insertData(String rollno, String name, String studentClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollno", rollno);
        contentValues.put("name", name);
        contentValues.put("studentClass", studentClass);
        db.insert("Students", null, contentValues);
        db.close();
    }

    public Cursor readData(){
        SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor= db.rawQuery("Select * from Students",null);
        return cursor;
    }

    public void updateData(String rollno,String name,String studentClass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("studentClass",studentClass);
        db.update("Students",contentValues,"rollno=?",new String[]{rollno});
    }

    public void deleteData(String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Students", "rollno=?", new String[]{rollno});
    }
}
