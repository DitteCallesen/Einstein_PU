package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TeachingActivity extends AppCompatActivity {
    private String name,username,position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching);


    final TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

    Bundle extras = getIntent().getExtras();
        name=extras.getString("name");
        username=extras.getString("username");
        position=extras.getString("position");
        String msg = "Welcome to Einstein, " + name + "!";
        welcomeMessage.setText(msg);



}

    // Class buttons
    public void fag1OnClick(View v) {

        Intent intent = new Intent(TeachingActivity.this, Class1Activity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        TeachingActivity.this.startActivity(intent);

    }

    public void fag2OnClick(View v) {
        Intent intent = new Intent(TeachingActivity.this, Class2Activity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        TeachingActivity.this.startActivity(intent);

    }

    // This method change to the ChatRoomActivity
    public void chatroomOnClick(View v) {
        Intent intent = new Intent(TeachingActivity.this, ListOfChatroomActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        TeachingActivity.this.startActivity(intent);

    }

    // This method change to the CalendarActivity
    public void calenderOnClick(View v) {
        Intent intent = new Intent(TeachingActivity.this, CalendarActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        TeachingActivity.this.startActivity(intent);

    }


    //logout
    public void goToLogin(View view) {
        Intent intent = new Intent(TeachingActivity.this, LoginActivity.class);
        TeachingActivity.this.startActivity(intent);
        finish();
    }

    //go to charts
    public void chartOnClick(View view) {

        final Response.Listener<String> responStringListener2 = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String coursesubject =jsonObject.toString();

                    Intent intent = new Intent(TeachingActivity.this, ChartActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("name", name);
                    extras.putString("username", username);
                    extras.putString("courseSubject", coursesubject);
                    extras.putString("position", position);
                    intent.putExtras(extras);
                    TeachingActivity.this.startActivity(intent);
                }
                catch (JSONException e1){
                    e1.printStackTrace();
                }
            }
        };
        TeachingActivity.CharDataRequest charDataRequest = new TeachingActivity.CharDataRequest(username, responStringListener2);
        RequestQueue queue = Volley.newRequestQueue(TeachingActivity.this);
        queue.add(charDataRequest);


    }

    public class CharDataRequest extends StringRequest {

        private static final String LOGIN_REQUEST_URL = "https://truongtrxu.000webhostapp.com/getCourseAndSubject.php";
        private Map<String, String> params;

        public CharDataRequest(String username, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("username", username);


        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

}
