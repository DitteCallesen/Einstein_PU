package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fag2Activity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String SUBJECT_ID = "subject_id";
    public final static String TASK_ID = "task_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag2);
    }

    public void button0_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 0);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button1_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 1);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button2_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 2);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button3_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 3);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button4_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 4);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button5_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 5);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button6_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 6);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void button7_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 7);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
