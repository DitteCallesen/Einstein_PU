package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fag2Activity extends AppCompatActivity {

    public final static String CLASS_ID = "com.example.myfirstapp.MESSAGE5";
    public final static String SUBJECT_ID = "com.example.myfirstapp.MESSAGE6";
    public final static String TASK_ID = "com.example.myfirstapp.MESSAGE7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag2);
    }

    public void button1_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 1);
        intent.putExtra(SUBJECT_ID, 0);
        intent.putExtra(TASK_ID, 0);
        startActivity(intent);
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
