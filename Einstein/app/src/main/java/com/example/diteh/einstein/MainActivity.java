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

        final TextView welcomemsg = (TextView) findViewById(R.id.studName);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String msg = name + " welcome to Einstein";
        welcomemsg.setText(msg);

    }

    // "FAG" buttons
    public void fag1OnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), Class1Activity.class));
    }

    public void fag2OnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), Class2Activity.class));
    }

    //Buttons - opens new windowes
    public void calenderOnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), CalenderActivity.class));
    }

    public void chatroomOnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), ChatroomActivity.class));
    }

    public void trophyOnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), TrophyActivity.class));
    }


}
