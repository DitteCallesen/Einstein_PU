package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalenderActivity extends AppCompatActivity {
    private String name, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        username=extras.getString("username");

    }

    public void backOnClick(View v){
        Intent intent = new Intent(CalenderActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        intent.putExtras(extras);
        startActivity(intent);

    }
}
