package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calenderOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),CalenderActivity.class));
    }

    public void chatroomOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),ChatroomActivity.class));
    }

    public void assignmentOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),AssignmentActivity.class));
    }
}
