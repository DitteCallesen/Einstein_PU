package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrophyActivity extends AppCompatActivity {

    List<Integer> activeTrophies = new ArrayList<>();
    DatabaseHelper myDb;
    public final static String CLASS_ID = "class_id";
    public final static String TASK = "task";
    public final static String SUBJECT_ID = "subject_id";

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        myDb = new DatabaseHelper(this);

        //Kommenter vekk denne linja for å slette alle troféer
        //myDb.removeTrophies(myDb.getWritableDatabase());
        getActiveTrophiesFromDatabase();
        showTrophies();

    }

    public void trophyClicked(View view) {
        LinearLayout big_trophy = (LinearLayout) findViewById(R.id.big_trophy);
        big_trophy.setVisibility(View.VISIBLE);
        TextView trophy_text = (TextView) findViewById(R.id.trophy_text);

        switch (view.getId()) {
            case R.id.trophy1:
                trophy_text.setText("Solved your first exercise.");
                break;
            case R.id.trophy2:
                trophy_text.setText("Answered correctly on 5 exercises in a row.");
                break;
            case R.id.trophy3:
                trophy_text.setText("Answered correctly on 10 exercises in a row.");
                break;
            case R.id.trophy4:
                trophy_text.setText("Worked 5 days in one week.");
                break;
            case R.id.trophy5:
                trophy_text.setText("Worked 5 days every week for 3 weeks.");
                break;
            case R.id.trophy6:
                trophy_text.setText("Worked 20 days in a month.");
                break;
            case R.id.trophy7:
                trophy_text.setText("Solved 5 exercises.");
                break;
            case R.id.trophy8:
                trophy_text.setText("Solved 10 exercises.");
                break;
            case R.id.trophy9:
                trophy_text.setText("Solved 50 exercises.");
                break;
            case R.id.trophy10:
                trophy_text.setText("Solved 100 exercises.");
                break;
            case R.id.trophy11:
                trophy_text.setText("Solved 500 exercises.");
                break;
            case R.id.trophy12:
                trophy_text.setText("Solved 1000 exercises.");
                break;

            default:
                //
        }
    }

    public void bigTrophyClicked(View view) {
        LinearLayout big_trophy = (LinearLayout) findViewById(R.id.big_trophy);
        big_trophy.setVisibility(View.INVISIBLE);
    }

    public void getActiveTrophiesFromDatabase() {

        Cursor cursor = myDb.getAllData();
        while (cursor.moveToNext()) {
            activeTrophies.add(cursor.getInt(0));
        }
    }

    public void showTrophies() {
        if (activeTrophies.contains(1)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy1);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(2)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy2);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(3)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy3);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(4)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy4);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(5)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy5);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(6)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy6);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(7)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy7);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(8)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy8);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(9)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy9);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(10)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy10);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(11)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy11);
            imageButton.setVisibility(View.VISIBLE);
        }
        if (activeTrophies.contains(12)) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.trophy12);
            imageButton.setVisibility(View.VISIBLE);
        }
    }


}



    /*
    public void backTOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void method() {
        //
    }


}
*/