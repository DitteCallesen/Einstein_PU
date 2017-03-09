package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String msg = "Welcome to Einstein, " + name + "!";
        welcomeMessage.setText(msg);

    }

    // Class buttons
    public void fag1OnClick(View v) {
        startActivity(new Intent(getApplicationContext(), Class1Activity.class));
    }

    public void fag2OnClick(View v) {
        startActivity(new Intent(getApplicationContext(), Class2Activity.class));
    }

    // This method change to the ChatRoomActivity
    public void chatroomOnClick(View v) {
        startActivity(new Intent(getApplicationContext(), ChatroomActivity.class));
    }

    // This method change to the CalendarActivity
    public void calenderOnClick(View v) {
        startActivity(new Intent(getApplicationContext(), CalenderActivity.class));
    }

    // This method change to the TrophyRoomActicity
    public void trophyOnClick(View v) {
        startActivity(new Intent(getApplicationContext(), TrophyActivity.class));
    }


}
