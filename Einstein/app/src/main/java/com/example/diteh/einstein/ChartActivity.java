package com.example.diteh.einstein;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {
    Button fillWithData;
    BarChart barChart;
    private int[] courseID, subjectID;
    private ArrayList<String> course, subject;
    protected String name, username, coursesubject, position;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        fillWithData = (Button) findViewById(R.id.getdata);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        username = extras.getString("username");
        position = extras.getString("position");
        coursesubject = extras.getString("courseSubject");

        try {
            JSONObject jsonObject = new JSONObject(coursesubject);
            JSONObject server_respons = jsonObject.getJSONObject("server_response");
            JSONArray courses = server_respons.getJSONArray("course");
            JSONArray subjects = server_respons.getJSONArray("subject");
            courseID = new int[courses.length()];
            subjectID = new int[subjects.length()];
            course = new ArrayList<>();
            subject = new ArrayList<>();
            for (int i = 0; i < courses.length(); i++) {
                JSONObject C = courses.getJSONObject(i);
                courseID[i] = C.getInt("courseID");
                course.add(C.getString("course"));
            }

            for (int j = 0; j < subjects.length(); j++) {
                JSONObject S = subjects.getJSONObject(j);
                subjectID[j] = S.getInt("subjectID");
                subject.add(S.getString("subject"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Spinner spin = (Spinner) findViewById(R.id.Scourse);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.MathSubjects, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.StatSubjects, android.R.layout.simple_spinner_item);
        final Spinner spin2 = (Spinner) findViewById(R.id.Ssubject);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectCourse = parent.getSelectedItem().toString();

                if (selectCourse.equals("Mathematics")) {
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adapter2);
                } else {
                    adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adapter21);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Spinner spin3 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adapter3);

        fillWithData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String courseName = spin.getSelectedItem().toString();
                final String subjectName = spin2.getSelectedItem().toString();
                final String choice = spin3.getSelectedItem().toString();
                int selSID = subjectID[subject.indexOf(subjectName)];
                int selCID = courseID[course.indexOf(courseName)];
                final String select;
                if (choice.equals("Per assignment")) {
                    select = "per";
                } else {
                    select = "assign";
                }
                TextView text = (TextView) findViewById(R.id.textCS);
                text.setText("Course: " + courseName + " subject: " + subjectName);

                final Response.Listener<String> reStringListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject server_response = jsonObject.getJSONObject("server_response");
                            setTable(server_response.toString(), select);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder errormsg = new AlertDialog.Builder(ChartActivity.this);
                            errormsg.setMessage("Error in loading data from database")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create();
                            errormsg.show();
                        }
                    }
                };
                GetDataToChart getDataToChart = new GetDataToChart(selCID, selSID, select, reStringListener);
                RequestQueue queue = Volley.newRequestQueue(ChartActivity.this);
                queue.add(getDataToChart);

            }
        });


    }


    public void btBack(View view) {
        Intent intent = new Intent(ChartActivity.this, TeachingActivity.class);
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
        Intent intent = new Intent(ChartActivity.this, TeachingActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }


    public void setTable(String response, String select) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> theDates = new ArrayList<>();
        BarDataSet barDataSet;
        TextView xlabel = (TextView) findViewById(R.id.xLabel);
        TextView ylabel = (TextView) findViewById(R.id.yLabel);
        barChart = (BarChart) findViewById(R.id.barchart);
        XAxis xAxis = barChart.getXAxis();
        YAxis yAxis = barChart.getAxisRight();

        try {
            JSONObject server_response = new JSONObject(response);
            JSONArray chartData = server_response.getJSONArray("chartData");
            if (select.equals("per")) {
                for (int i = 0; i < chartData.length(); i++) {
                    JSONObject rowData = chartData.getJSONObject(i);
                    barEntries.add(new BarEntry(rowData.getInt("Solved"), i));
                    theDates.add(rowData.getString("assignID"));
                }
                xlabel.setText("Xaxis: AssigmentID");
                ylabel.setText("Yaxis:#solved assignments");
            } else {
                for (int i = 0; i < chartData.length(); i++) {
                    JSONObject rowData = chartData.getJSONObject(i);
                    barEntries.add(new BarEntry(rowData.getInt("NumOFSolved"), i));
                    theDates.add(rowData.getString("mmyy"));
                }
                xlabel.setText("Xaxis:month and year (mmyy)");
                ylabel.setText("Yaxis:#solved assignments");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        xAxis.setGridLineWidth(1);
        yAxis.setGridLineWidth(1);
        barChart.clear();
        barDataSet = new BarDataSet(barEntries, "Solved assignments");


        BarData theData = new BarData(theDates, barDataSet);

        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.invalidate();
    }


    public class GetDataToChart extends StringRequest {

        private static final String LOGIN_REQUEST_URL = "https://truongtrxu.000webhostapp.com/getCharDataValues.php";
        private Map<String, String> params;

        public GetDataToChart(int coursID, int subjectID, String select, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("courseID", Integer.toString(coursID));
            params.put("subjectID", Integer.toString(subjectID));
            params.put("select", select);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }


}
