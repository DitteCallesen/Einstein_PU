package com.example.diteh.einstein;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

public class Class1Activity extends AppCompatActivity {

    private final static String CLASS_ID = "classId";
    private final static String SUBJECT_ID = "subjectId";
    private final static String TASK_ID = "taskId";
    private final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";
    private final static String CORRECT_ON_FIRST_TRY = "correctOnFirstTry";
    private final static String NUMBER_OF_TASKS = "numberOfTasks";
    private String username, name, position;
    private String JSON_STRING;
    private String js_string;
    private String classId = "Mathematics", subjectId;
    private int Asolved;
    private int[] solved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class1);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position=extras.getString("position");
    }


    public void backToMain(View v) {
        Intent intent;
        if(position.equals("Student")){
            intent = new Intent(Class1Activity.this, MainActivity.class);
        }
        else{
            intent = new Intent(Class1Activity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        Class1Activity.this.startActivity(intent);
        finish();
    }

    //use anndroid back button
    @Override
    public void onBackPressed() {
        Intent intent;
        if(position.equals("Student")){
            intent = new Intent(Class1Activity.this, MainActivity.class);
        }
        else{
            intent = new Intent(Class1Activity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }

    public void getJson(View view) {
        Button b = (Button) view;
        subjectId = b.getText().toString();
        new Background(classId, subjectId).execute();

    }

    class Background extends AsyncTask<Void, Void, String> {
        //Gets data from database
        String classId, subjectId;
        String json_url;
        int CorrAnsIn, taskID, correctOnFirstTry, numberOfTasks;

        //krever at kurs navn og emnet blir lagt til
        public Background(String classId, String subjectId) {
            this.classId = classId;
            this.subjectId = subjectId;

        }

        @Override
        protected void onPreExecute() {
            //url for php that fetch data from database, comes back as json object
            json_url = "https://truongtrxu.000webhostapp.com/getJsonAssign.php?course=" + classId + "&subject=" + subjectId + "&username=" + username;
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
                //prepear data for sending to assignment activity
                JSONObject jsonObject = new JSONObject(js_string);
                JSONObject server_response = jsonObject.getJSONObject("server_response");
                JSONArray userdataArray = server_response.getJSONArray("userdata");
                JSONObject userdata = userdataArray.getJSONObject(0);

                CorrAnsIn = userdata.getInt("ansInARow");
                taskID = userdata.getInt("taskID");
                correctOnFirstTry = userdata.getInt("correctOnFirstTry");
                numberOfTasks = server_response.getJSONArray("assignments").length();
                Asolved = userdata.getInt("Asolved");
                solved = new int[numberOfTasks];

                Intent intent = new Intent(Class1Activity.this, AssignmentActivity.class);
                Bundle extras = new Bundle();
                extras.putString(CLASS_ID, classId);
                extras.putString(SUBJECT_ID, subjectId);
                extras.putInt(TASK_ID, taskID);
                extras.putInt(CORRECT_ANSWERS_IN_A_ROW, CorrAnsIn);
                extras.putInt(CORRECT_ON_FIRST_TRY, correctOnFirstTry);
                extras.putInt(NUMBER_OF_TASKS, numberOfTasks);
                extras.putString("jsonO", server_response.toString());
                extras.putString("name", name);
                extras.putString("username", username);
                extras.putIntArray("solved", solved);
                extras.putInt("Asolved", Asolved);
                extras.putString("position", position);
                intent.putExtras(extras);
                Class1Activity.this.startActivity(intent);
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
