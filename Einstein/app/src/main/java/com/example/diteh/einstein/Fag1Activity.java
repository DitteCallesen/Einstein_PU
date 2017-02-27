package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fag1Activity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String SUBJECT_ID = "subject_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag1);
    }

    public void button1_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 0);
        intent.putExtra(SUBJECT_ID, 0);
        startActivity(intent);
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
