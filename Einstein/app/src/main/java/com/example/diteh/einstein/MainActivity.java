package com.example.diteh.einstein;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

protected String name,username, position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position = extras.getString("position");
        String msg = "Welcome to Einstein, " + name + "!";
        welcomeMessage.setText(msg);
    }

    // Class buttons
    public void class1OnClick(View v) {
        Intent intent = new Intent(MainActivity.this, Class1Activity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        MainActivity.this.startActivity(intent);

    }

    public void class2OnClick(View v) {
        Intent intent = new Intent(MainActivity.this, Class2Activity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        MainActivity.this.startActivity(intent);

    }

    // This method change to the ChatRoomActivity
    public void chatroomOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, ListOfChatroomActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        MainActivity.this.startActivity(intent);

    }

    // This method change to the CalendarActivity
    public void calendarOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        MainActivity.this.startActivity(intent);

    }

    // This method get user trophies to the TrophyRoomActicity
    public void trophyOnClick(View v) {
        new Background(username).execute();
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }

    class Background extends AsyncTask<Void, Void, String> {
        //Gets data from database
        String username;
        String JSON_STRING, js_string;
        String json_url;

        //get input data
        public Background(String username) {
            this.username = username;
        }

        @Override
        protected void onPreExecute() {
            //reset assignments so user can start over


            //url for php that fetch data from database, comes back as json object
            json_url = "https://truongtrxu.000webhostapp.com/getTrophies.php?username=" + username;
        }

        @Override
        protected String doInBackground(Void... params) {
            //open line to database

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
            js_string = result;
            try {
                JSONObject jsonObject = new JSONObject(js_string);
                JSONObject server_response = jsonObject.getJSONObject("server_response");

                Intent intent = new Intent(MainActivity.this, TrophyActivity.class);

                Bundle extras = new Bundle();
                extras.putString("jsonO", server_response.toString());
                extras.putString("name", name);
                extras.putString("username", username);
                extras.putString("position", position);
                intent.putExtras(extras);


                MainActivity.this.startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
