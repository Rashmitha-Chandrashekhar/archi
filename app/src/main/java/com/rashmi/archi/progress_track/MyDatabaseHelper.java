package com.rashmi.archi.progress_track;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Progress_Track.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME1 = "MainPage";
    private static final String TABLE_NAME2 = "SubPage";
    private static final String COLUMN_MAINID = "_id";
    private static final String COLUMN_SUBID = "_id";
    private static final String COLUMN_MAINTITLE = "main_title";
    private static final String COLUMN_MAINCOMMENT = "main_comment";
    private static final String COLUMN_MAINMARKS = "main_marks";
    private static final String COLUMN_SUBTITLE = "sub_title";
    private static final String COLUMN_SUBCOMMENT = "sub_comment";
    private static final String COLUMN_SUBMARKS = "sub_marks";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE "
                        + TABLE_NAME1 + " ("
                        + COLUMN_MAINID + " INTEGER  , "
                        + COLUMN_MAINTITLE+ " TEXT, "
                        + COLUMN_MAINCOMMENT + " TEXT, "
                        + COLUMN_MAINMARKS + " INTEGER ,"
                + " FOREIGN KEY ("+COLUMN_MAINID+") REFERENCES "+TABLE_NAME2+" ("+COLUMN_SUBID+"));";

        String query2 =  "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_SUBID + " INTEGER , " +
                COLUMN_SUBTITLE + " TEXT, " +
                COLUMN_SUBCOMMENT + " TEXT, " +
                COLUMN_SUBMARKS + " INTEGER);";
        db.execSQL(query1);
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void addmain(String title, String comment, int marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_MAINID,2);
        cv1.put(COLUMN_MAINTITLE, title);
        cv1.put(COLUMN_MAINCOMMENT,comment);
        cv1.put(COLUMN_MAINMARKS, marks);
        long result = db.insert(TABLE_NAME1,null, cv1);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void addsub(String title, String comment, int marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();
        cv2.put(COLUMN_SUBID,1);
        cv2.put(COLUMN_SUBTITLE, title);
        cv2.put(COLUMN_SUBCOMMENT,comment);
        cv2.put(COLUMN_SUBMARKS, marks);
        long result = db.insert(TABLE_NAME2,null, cv2);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readmainData(){
        String query1= "SELECT * FROM " + TABLE_NAME1;

        SQLiteDatabase db1 = this.getReadableDatabase();

        Cursor cursor1 = null;
        if(db1 != null){
            cursor1 = db1.rawQuery(query1, null);
        }
        return cursor1;
    }

    Cursor readsubData(){
        String query2 = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db2 = this.getReadableDatabase();

        Cursor cursor2 = null;
        if(db2 != null){
            cursor2 = db2.rawQuery(query2, null);
        }
        return cursor2;

    }
    void updateData(String row_id, String title1, String comment1, String marks1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_SUBTITLE, title1);
        cv1.put(COLUMN_SUBCOMMENT,comment1);
        cv1.put(COLUMN_SUBMARKS, marks1);

        long result = db.update(TABLE_NAME2, cv1, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME1, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
        long result1 = db.delete(TABLE_NAME2, "_id=?", new String[]{row_id});
        if(result1 == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1);
        db.execSQL("DELETE FROM " + TABLE_NAME2);
    }

}
