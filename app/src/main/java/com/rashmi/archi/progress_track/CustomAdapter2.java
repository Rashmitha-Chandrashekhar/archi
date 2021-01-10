package com.rashmi.archi.progress_track;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder1>{
    private Context context1;
    private Activity activity1;
    private ArrayList sub_id, sub_title, sub_comment,sub_marks;

    CustomAdapter2(Activity activity1, Context context1, ArrayList sub_id, ArrayList sub_title, ArrayList sub_comment,
                   ArrayList sub_marks){
        this.activity1 = activity1;
        this.context1 = context1;
        this.sub_id = sub_id;
        this.sub_title = sub_title;
        this.sub_comment = sub_comment;
        this.sub_marks =sub_marks;
    }
    @NonNull
    @Override
    public CustomAdapter2.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context1);
        View view = inflater.inflate(R.layout.my_row1, parent, false);
        return new CustomAdapter2.MyViewHolder1(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter2.MyViewHolder1 holder, int position) {
        holder.sub_id_txt.setText(String.valueOf(sub_id.get(position)));
        holder.sub_title_txt.setText(String.valueOf(sub_title.get(position)));
        holder.sub_comment_txt.setText(String.valueOf(sub_comment.get(position)));
        holder.sub_marks_txt.setText(String.valueOf(sub_marks.get(position)));
        holder.mainLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context1, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(sub_id.get(position)));
                intent.putExtra("title", String.valueOf(sub_title.get(position)));
                intent.putExtra("comment", String.valueOf(sub_comment.get(position)));
                intent.putExtra("marks", String.valueOf(sub_marks.get(position)));
                activity1.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView sub_id_txt, sub_title_txt, sub_comment_txt,sub_marks_txt;
        LinearLayout mainLayout1;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            sub_id_txt = itemView.findViewById(R.id.sub_id_txt);
            sub_title_txt = itemView.findViewById(R.id.sub_title_txt);
            sub_comment_txt = itemView.findViewById(R.id.sub_comment_txt);
            sub_marks_txt = itemView.findViewById(R.id.sub_marks_txt);
            mainLayout1 = itemView.findViewById(R.id.mainLayout1);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context1, R.anim.translate_anim);
            mainLayout1.setAnimation(translate_anim);
        }
    }


}
