package com.rashmi.archi.progress_track;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText sub_title, sub_comment, sub_marks;
    Button update_button, delete_button;

    String id1, title1, comment1, marks1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sub_title = findViewById(R.id.sub_title);
        sub_comment = findViewById(R.id.sub_comment);
        sub_marks = findViewById(R.id.sub_marks);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title1 after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title1);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(com.rashmi.archi.progress_track.UpdateActivity.this);
                title1 = sub_title.getText().toString().trim();
                comment1 = sub_comment.getText().toString().trim();
                marks1 = sub_marks.getText().toString().trim();
                myDB.updateData(id1, title1, comment1, marks1);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id1") && getIntent().hasExtra("title1") &&
                getIntent().hasExtra("comment1") && getIntent().hasExtra("marks1")){
            //Getting Data from Intent
            id1 = getIntent().getStringExtra("id1");
            title1 = getIntent().getStringExtra("title1");
            comment1 = getIntent().getStringExtra("comment1");
            marks1 = getIntent().getStringExtra("marks1");

            //Setting Intent Data
            sub_title.setText(title1);
            sub_comment.setText(comment1);
            sub_marks.setText(marks1);
            Log.d("stev", title1+" "+comment1+" "+marks1);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title1 + " ?");
        builder.setMessage("Are you sure you want to delete " + title1 + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(com.rashmi.archi.progress_track.UpdateActivity.this);
                myDB.deleteOneRow(id1);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
