package com.example.diteh.einstein;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrophyActivity extends AppCompatActivity {

    private String name, username, position;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position = extras.getString("position");


        //make an array of all the activated trophies
        try {
            JSONObject server_response = new JSONObject(extras.getString("jsonO"));
            JSONArray trophies = server_response.getJSONArray("trophy");

            for (int i = 0; i < trophies.length(); i++) {
                JSONObject getTrophy = trophies.getJSONObject(i);
                showTrophies(getTrophy.getInt("trophynum"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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


    public void showTrophies(int i) {

        switch (i) {
            case 1:
                imageButton = (ImageButton) findViewById(R.id.trophy1);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 2:
                imageButton = (ImageButton) findViewById(R.id.trophy2);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 3:
                imageButton = (ImageButton) findViewById(R.id.trophy3);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 4:
                imageButton = (ImageButton) findViewById(R.id.trophy4);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 5:
                imageButton = (ImageButton) findViewById(R.id.trophy5);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 6:
                imageButton = (ImageButton) findViewById(R.id.trophy6);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 7:
                imageButton = (ImageButton) findViewById(R.id.trophy7);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 8:
                imageButton = (ImageButton) findViewById(R.id.trophy8);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 9:
                imageButton = (ImageButton) findViewById(R.id.trophy9);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 10:
                imageButton = (ImageButton) findViewById(R.id.trophy10);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 11:
                imageButton = (ImageButton) findViewById(R.id.trophy11);
                imageButton.setVisibility(View.VISIBLE);
                break;
            case 12:
                imageButton = (ImageButton) findViewById(R.id.trophy12);
                imageButton.setVisibility(View.VISIBLE);
                break;
            default:

        }
    }

    public void backButtonOnClick(View v) {
        Intent intent = new Intent(TrophyActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }

    //use Android back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrophyActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
}




