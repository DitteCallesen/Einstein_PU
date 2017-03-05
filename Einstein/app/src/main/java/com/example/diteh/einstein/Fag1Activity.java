package com.example.diteh.einstein;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Fag1Activity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String TASK_ID = "task_id";
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

        new Background("Matematikk","Algebra",1).execute();
    }

    public void Back1OnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void getJson(View view) {


    }

    class Background extends AsyncTask<Void, Void, String>{
        //Henter data fra database
        String class_id,subject_id;
        String json_url;
        int CorrAnsIn;

        //krever at kurs navn og emnet blir lagt til
        public Background(String class_id,String subject_id, int CorrAnsIn) {
            this.class_id = class_id;
            this.subject_id=subject_id;
            this.CorrAnsIn = CorrAnsIn;
        }

        @Override
        protected void onPreExecute() {
        //url for php som henter data fra database, kommer tilbake som json objekt
            json_url= "https://truongtrxu.000webhostapp.com/json_get_data.php?course="+class_id +"&subject="+subject_id;
        }
        @Override
        protected String doInBackground(Void... params) {
            //åpner linje til database
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
            //sender jsonobjekt til assignment aktivitet som string
            js_string= result;
            try {
                //funnet data fra database, sender info til assignment aktivitet
                JSONObject jsonObject = new JSONObject(js_string);
                Intent intent = new Intent(Fag1Activity.this, AssignmentActivity.class);
                intent.putExtra("jsonO", jsonObject.toString());
                intent.putExtra(CLASS_ID, class_id);
                intent.putExtra(SUBJECT_ID, subject_id);
                intent.putExtra(CORRECT_ANSWERS_IN_A_ROW, CorrAnsIn);
                intent.putExtra(TASK_ID, 0);
                Fag1Activity.this.startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
