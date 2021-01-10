package com.rashmi.archi.progress_track;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity1 extends AppCompatActivity {
    EditText sub_title,sub_comment,sub_marks;
    Button sub_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add1);
        sub_title = findViewById(R.id.sub_title);
        sub_comment = findViewById(R.id.sub_comment);
        sub_marks = findViewById(R.id.sub_marks);
        sub_button = findViewById(R.id.sub_button);
        sub_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity1.this);
                myDB.addsub(sub_title.getText().toString().trim(),
                        sub_comment.getText().toString().trim(),
                        Integer.valueOf(sub_marks.getText().toString().trim()));

            }
        });
    }
}