package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.AsyncTask;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrophyActivity extends AppCompatActivity {

    String name, username;
    public int[] myTrophies;
    ImageButton imageButton;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        username=extras.getString("username");

        //make an array of all the activated trophies
        try {
            JSONObject server_response = new JSONObject(extras.getString("jsonO"));
            JSONArray trophies= server_response.getJSONArray("trophy");
            int y = trophies.length();
            myTrophies = new int[trophies.length()];

            for (int i =0;i<trophies.length();i++){
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
                trophy_text.setText("Gjort din første oppgave.");
                break;
            case R.id.trophy2:
                trophy_text.setText("Fått riktig på 5 oppgaver på rad.");
                break;
            case R.id.trophy3:
                trophy_text.setText("Løst 10 oppgaver på én dag.");
                break;
            case R.id.trophy4:
                trophy_text.setText("Fått riktig på 10 oppgaver på rad.");
                break;
            case R.id.trophy5:
                trophy_text.setText("Løst 10 oppgaver.");
                break;
            case R.id.trophy6:
                trophy_text.setText("Løst 10 oppgaver på én time.");
                break;
            case R.id.trophy7:
                trophy_text.setText("Løst 10 oppgaver på en dag.");
                break;
            case R.id.trophy8:
                trophy_text.setText("Løst 30 oppgaver på én dag.");
                break;
            case R.id.trophy9:
                trophy_text.setText("Løst 50 oppgaver.");
                break;
            case R.id.trophy10:
                trophy_text.setText("Fått riktig på 30 oppgaver på rad.");
                break;
            case R.id.trophy11:
                trophy_text.setText("Løst 100 oppgaver.");
                break;
            case R.id.trophy12:
                trophy_text.setText("Løst 500 oppgaver.");
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

    switch (i){
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


}




