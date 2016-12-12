package com.example.tonghung.sqlitedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tonghung.sqlitedemo.object.Student;

/**
 * Created by tonghung on 12/12/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, "myDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student_tb(id_std integer primary key autoincrement, name_std text not null, age_std" +
                " " +
                "integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists student_tb");
        onCreate(db);
    }

    public long insertStudent(Student std){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_std", std.getName());
        contentValues.put("age_std", std.getAge());
        return db.insert("student_tb", null, contentValues);
    }

    public Cursor getAllStudents(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(false,"student_tb", null, null, null, null, null, null, null);
    }
}
