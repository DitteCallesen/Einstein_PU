package com.example.diteh.einstein;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Class2Activity extends AppCompatActivity {

    public final static String CLASS_ID = "classId";
    public final static String SUBJECT_ID = "subjectId";
    public final static String TASK_ID = "taskId";
    public final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";

    String JSON_STRING;
    String js_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag2);
    }

    public void button0_clicked(View view) {
        new Background("Statistics", "Mean", 0).execute();
    }

    public void button1_clicked(View view) {
        new Background("Statistics", "Median", 0).execute();
    }

    public void button2_clicked(View view) {
        new Background("Statistics", "Variance", 0).execute();
    }

    public void button3_clicked(View view) {
        new Background("Statistics", "Standarddeviation", 0).execute();
    }

    public void button4_clicked(View view) {
        new Background("Statistics", "AdditiveRules", 0).execute();
    }

    public void button5_clicked(View view) {

        new Background("Statistics", "Product Rule", 0).execute();
    }

    public void button6_clicked(View view) {

        new Background("Statistics", "Bayes Rule", 0).execute();
    }

    public void button7_clicked(View view) {
        new Background("Statistics", "Mean of a random variable", 0).execute();
    }

    class Background extends AsyncTask<Void, Void, String> {
        //Henter data fra database
        String classId, subjectId;
        String json_url;
        int CorrAnsIn;

        //krever at kurs navn og emnet blir lagt til
        public Background(String classId, String subjectId, int CorrAnsIn) {
            this.classId = classId;
            this.subjectId = subjectId;
            this.CorrAnsIn = CorrAnsIn;
        }

        @Override
        protected void onPreExecute() {
            //url for php som henter data fra database, kommer tilbake som json objekt
            json_url = "https://truongtrxu.000webhostapp.com/json_get_data.php?course=" + classId + "&subject=" + subjectId;
        }

        @Override
        protected String doInBackground(Void... params) {
            //åpner linje til database
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            js_string = result;
            try {
                //funnet data fra database, sender info til assignment aktivitet
                JSONObject jsonObject = new JSONObject(js_string);
                Intent intent = new Intent(Class2Activity.this, AssignmentActivity.class);
                intent.putExtra("jsonO", jsonObject.toString());
                intent.putExtra(CLASS_ID, classId);
                intent.putExtra(SUBJECT_ID, subjectId);
                intent.putExtra(CORRECT_ANSWERS_IN_A_ROW, CorrAnsIn);
                intent.putExtra(TASK_ID, 0);
                Class2Activity.this.startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void backToMain(View v) {
        Intent intent = new Intent(Class2Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
