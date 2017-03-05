package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class AssignmentActivity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String SUBJECT_ID = "subject_id";
    public final static String TASK_ID = "task_id";
    public final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";

    String class_id, subject_id, jstring;
    JSONArray jsonArray;
    JSONObject jsonObject;
    int globalCounter;
    int correctAnswersInARow;
    public static int counterC=0;
    public static int counterW=0;
    //DatabaseHelper myDb;

    String correctAnswer = "";
    String answer1 = "";
    String answer2 = "";
    String answer3 = "";
    String answer4 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        //myDb = new DatabaseHelper(this);

        //Får jsonobjekt forrige aktivitet
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("jsonO"));
            jsonArray = jsonObject.getJSONArray("server_response");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Bundle extras = getIntent().getExtras();
        class_id = extras.getString(CLASS_ID);
        subject_id = extras.getString(SUBJECT_ID);
        int task_id = extras.getInt(TASK_ID);
        correctAnswersInARow = extras.getInt(CORRECT_ANSWERS_IN_A_ROW);


        TextView class_view = (TextView) findViewById(R.id.fag);
        TextView subject_view = (TextView) findViewById(R.id.subject);
        TextView question_view = (TextView) findViewById(R.id.question);
        TextView button1 = (TextView) findViewById(R.id.button1);
        TextView button2 = (TextView) findViewById(R.id.button2);
        TextView button3 = (TextView) findViewById(R.id.button3);
        TextView button4 = (TextView) findViewById(R.id.button4);
        TextView button5 = (TextView) findViewById(R.id.button5);
        if(task_id==0){counterC=0;counterW=0;}

        //Her kan vi hente ut neste spørsmål fra database med task_id
        if (nextTaskExists(jsonArray, task_id)) {
            List<String> task = nextTask(jsonArray, task_id);
            class_view.setText(class_id);
            subject_view.setText(subject_id);
            question_view.setText(task.get(0));
            List<String> answers = randomizer(task.get(1), task.get(2), task.get(3), task.get(4));
            button1.setText(answers.get(1));
            button2.setText(answers.get(2));
            button3.setText(answers.get(3));
            button4.setText(answers.get(4));
            button5.setVisibility(View.INVISIBLE);
            answer1 = answers.get(1);
            answer2 = answers.get(2);
            answer3 = answers.get(3);
            answer4 = answers.get(4);
            correctAnswer = answers.get(0);

        }
        else {
            subject_view.setText("Du er ferdig");
            question_view.setText("Kor/Feil =" + counterC +"/"+ counterW);
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);
            button5.setVisibility(View.VISIBLE);

        }


        globalCounter = task_id;

    }

    //Denne metoden avgjør om det finnes en oppgave til i databasen
    public boolean nextTaskExists(JSONArray jsonArray, int task_id) {

        if(task_id<jsonArray.length()){
            return true;
        }
        else{
            return false;
        }

    }

    //Finner riktig oppgave i databasen
    //Returnerer med en liste med all info om oppgaven
    public List<String> nextTask(JSONArray jsonArray, int task_id) {
        List<String> foo = new ArrayList<String>(Arrays.asList( "f", "f", "f", "f", "f"));
        String oppgaver="";
        try {
            JSONObject jo = jsonArray.getJSONObject(task_id);
            oppgaver = jo.getString("task");
            String[] task_array = oppgaver.split("_");
            List<String> task_list = Arrays.asList(task_array);
            return task_list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foo;

    }



    public void correctAnswerClicked() {
        counterC++;
        LinearLayout correct_answer_view = (LinearLayout) findViewById(R.id.correct_answer);
        correct_answer_view.setVisibility(View.VISIBLE);

//        if (correctAnswersInARow == 5 && !myDb.containsTrophy(2)) {
//            addTrophy(2);
//            Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
//        }
//        if (correctAnswersInARow == 10 && !myDb.containsTrophy(4)) {
//            addTrophy(4);
//        }
//        if (correctAnswersInARow == 30 && !myDb.containsTrophy(10)) {
//            addTrophy(10);
//        }
    }

    public void goToNextTask(View view) {

        Intent intent = new Intent(this, AssignmentActivity.class);
        Bundle extras = new Bundle();
        int task_id = globalCounter + 1;
        extras.putString(CLASS_ID, class_id);
        extras.putString(SUBJECT_ID, subject_id);
        extras.putInt(TASK_ID, task_id);
        extras.putInt(CORRECT_ANSWERS_IN_A_ROW, correctAnswersInARow + 1);
        extras.putString("jsonO", jsonObject.toString());
        intent.putExtras(extras);
        startActivity(intent);
    }

    public int countC(){
        counterC++;
        return counterC;
    }


    public int countW(){
        return counterW++;
    }

    public void wrongAnswerClicked() {
        countW();
        Context context = AssignmentActivity.this;
        String message = "Wrong";
        correctAnswersInARow = 1;
    }

    public void button1Clicked(View view) {
        if (answer1.equals(correctAnswer)) {
            correctAnswerClicked();
        }
        else {

            wrongAnswerClicked();

        }
    }

    public void button2Clicked(View view) {
        if (answer2.equals(correctAnswer)) {

            correctAnswerClicked();
        }
        else {

            wrongAnswerClicked();
        }
    }

    public void button3Clicked(View view) {
        if (answer3.equals(correctAnswer)) {

            correctAnswerClicked();
        }
        else {

            wrongAnswerClicked();
        }
    }

    public void button4Clicked(View view) {
        if (answer4.equals(correctAnswer)) {

            correctAnswerClicked();
        }
        else {

            wrongAnswerClicked();
        }
    }

    public void BackToMain(View v){
        Button button= (Button) v;
        Intent intent = new Intent(AssignmentActivity.this,MainActivity.class);
        startActivity(intent);
    }



    //Tar inn fire svaralternativer og plasserer dem i
    //tilfeldig rekkefølge i en liste som blir returnert
    //Riktig svaralternativ på plass 0
    //Alle svaralternativ på plass 1-4
    public List<String> randomizer(String s1, String s2, String s3, String s4) {
        List<String> list = new ArrayList<>(Arrays.asList(s1, s2, s3, s4));
        List<String> randomList = new ArrayList<>(Arrays.asList(s1));
        Random rand = new Random();
        for (int i = 4; i > 0; i--) {
            randomList.add(list.remove(rand.nextInt(i)));
        }
        return randomList;
    }

//    public void addTrophy(int trophyNumber) {
//        myDb.insertData(trophyNumber);
//    }





}





/*
package com.example.diteh.einstein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
    }


    public void backOnClick(View v){
        Button button= (Button) v;
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}


*/