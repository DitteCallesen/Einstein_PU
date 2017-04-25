package com.example.diteh.einstein;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Class1Activity extends AppCompatActivity implements View.OnClickListener{

    private final static String CLASS_ID = "classId";
    private final static String SUBJECT_ID = "subjectId";
    private final static String TASK_ID = "taskId";
    private final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";
    private final static String CORRECT_ON_FIRST_TRY = "correctOnFirstTry";
    private final static String NUMBER_OF_TASKS = "numberOfTasks";
    protected String username, name, position;
    protected int CorrAnsIn,taskID,correctOnFirstTry,numberOfTasks;
    protected String classId = "Mathematics";
    private int Asolved;
    private int[] solved;
    private Button button1, button2, button3, button4, button5, button6, button7, button8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class1);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position = extras.getString("position");
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }


    public void backToMain(View v) {
        Intent intent;
        if (position.equals("Student")) {
            intent = new Intent(Class1Activity.this, MainActivity.class);
        } else {
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

    //use Android back button
    @Override
    public void onBackPressed() {
        Intent intent;
        if (position.equals("Student")) {
            intent = new Intent(Class1Activity.this, MainActivity.class);
        } else {
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

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        final String subjectId = b.getText().toString();
        final String Classid = classId;

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //prepare data for sending to assignment activity
                    JSONObject jsonObject = new JSONObject(response);
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
        };
        AssigmentRequest assigmentRequest= new AssigmentRequest(username, Classid, subjectId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Class1Activity.this);
        queue.add(assigmentRequest);
    }


    public class AssigmentRequest extends StringRequest {

        private static final String ASSIGN_REQUEST_URL = "https://truongtrxu.000webhostapp.com/getJsonAssign2.php";
        private Map<String, String> params;

        public AssigmentRequest(String username, String course, String subject, Response.Listener<String> listener) {
            super(Request.Method.POST, ASSIGN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("username", username);
            params.put("course", course);
            params.put("subject", subject);

        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

}
