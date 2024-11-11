package com.example.crud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText rollno,name,studentClass;
    Button insert ,update,read,delete;

    TextView textView;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper =new DbHelper(this);
        rollno=findViewById(R.id.roll_no);
        name=findViewById(R.id.studentName);
        studentClass=findViewById(R.id.studentClass);
        insert=findViewById(R.id.createStudent);
        update=findViewById(R.id.updateStudent);
        read=findViewById(R.id.readStudent);
        delete=findViewById(R.id.deleteStudent);
        textView=findViewById(R.id.results);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String roll=rollno.getText().toString();
            String student_name=name.getText().toString();
            String student_class=studentClass.getText().toString();

            dbHelper.insertData(roll,student_name,student_name);
                Toast.makeText(getApplicationContext(), "Data Inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll="",student_name="",student_class="";

                Cursor cursor   =dbHelper.readData();
                while(cursor.moveToNext()){
                    roll=cursor.getString(0);
                    student_name=cursor.getString(1);
                    student_class=cursor.getString(2);
                }
                textView.setText("Roll_no:"+roll+"\t name:"+student_name+"\t class:"+student_class);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=rollno.getText().toString();
                String student_name=name.getText().toString();
                String student_class=studentClass.getText().toString();
                dbHelper.updateData(roll,student_name,student_class);
                Toast.makeText(MainActivity.this, "Data Updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=rollno.getText().toString();
                dbHelper.deleteData(roll);
                Toast.makeText(MainActivity.this, "Data Deleted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}