package com.example.diteh.einstein;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CalendarActivity extends AppCompatActivity {
    private String name, username,position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position=extras.getString("position");

    }

    public void backOnClick(View v) {
        Intent intent;
        if(position.equals("Student")){
            intent = new Intent(CalendarActivity.this, MainActivity.class);
        }
        else{
            intent = new Intent(CalendarActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        CalendarActivity.this.startActivity(intent);
        finish();
    }

    //use anndroid back button
    @Override
    public void onBackPressed() {
        Intent intent;
        if(position.equals("Student")){
            intent = new Intent(CalendarActivity.this, MainActivity.class);
        }
        else{
            intent = new Intent(CalendarActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
}
