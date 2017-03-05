package com.example.diteh.einstein;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Fag1Activity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String SUBJECT_ID = "subject_id";
    public final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";
    String JSON_STRING;
    String js_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag1);
    }

    public void button1_clicked(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra(CLASS_ID, 0);
        intent.putExtra(SUBJECT_ID, 0);
        intent.putExtra(CORRECT_ANSWERS_IN_A_ROW, 1);
        startActivity(intent);
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void getJson(View view) {
        new Background().execute();


    }

    class Background extends AsyncTask<Void, Void, String>{
        String json_url;
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!= null){



                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            js_string= result;
        }
    }




}
