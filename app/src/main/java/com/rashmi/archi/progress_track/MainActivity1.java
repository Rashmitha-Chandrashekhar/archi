package com.rashmi.archi.progress_track;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {
   RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String>sub_id,sub_title, sub_comment, sub_marks;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        recyclerView = findViewById(R.id.recyclerView1);
        add_button = findViewById(R.id.sub_button);
        empty_imageview = findViewById(R.id.empty_imageview1);
        no_data = findViewById(R.id.no_data1);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.rashmi.archi.progress_track.MainActivity1.this, AddActivity1.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(com.rashmi.archi.progress_track.MainActivity1.this);
       sub_id = new ArrayList<>();
       sub_title = new ArrayList<>();
        sub_comment = new ArrayList<>();
        sub_marks = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(com.rashmi.archi.progress_track.MainActivity1.this,this,sub_id,sub_title, sub_comment,
                sub_marks);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(com.rashmi.archi.progress_track.MainActivity1.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor1 = myDB.readsubData();
        if(cursor1.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor1.moveToNext()){
               sub_id.add(cursor1.getString(0));
               sub_title.add(cursor1.getString(1));
                sub_comment.add(cursor1.getString(2));
                sub_marks.add(cursor1.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(com.rashmi.archi.progress_track.MainActivity1.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(com.rashmi.archi.progress_track.MainActivity1.this, com.rashmi.archi.progress_track.MainActivity1.class);
                startActivity(intent);
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
