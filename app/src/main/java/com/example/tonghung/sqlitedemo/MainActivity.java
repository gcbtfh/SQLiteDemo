package com.example.tonghung.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tonghung.sqlitedemo.database.DatabaseHelper;
import com.example.tonghung.sqlitedemo.object.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etAge;
    private Button btnSubmit;
    private ListView lv;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<Student> listStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editTextName);
        etAge = (EditText) findViewById(R.id.editTextAge);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        lv = (ListView) findViewById(R.id.listView);

        insertStudent();
    }

    private void insertStudent(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.insertStudent(new Student(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()
                ))) == -1){
                    Toast.makeText(MainActivity.this, "Insert student failure", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Insert student successful", Toast.LENGTH_SHORT).show();
                    showStudents();
                    // clear form
                    etName.setText(null);
                    etAge.setText(null);
                }
            }
        });
    }

    private void showStudents(){
        Cursor c = db.getAllStudents();
        listStd = new ArrayList<>();
        while(c.moveToNext()){
            listStd.add(new Student(c.getString(1), c.getInt(2)));
        }

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listStd);
        lv.setAdapter(adapter);
    }
}
