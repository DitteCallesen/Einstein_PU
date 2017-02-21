package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fag1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag1);
    }

    public void assignmentOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),AssignmentActivity.class));
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
