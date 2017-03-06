package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AssignmentActivity extends AppCompatActivity {

    public final static String CLASS_ID = "classId";
    public final static String SUBJECT_ID = "subjectId";
    public final static String TASK_ID = "taskId";
    public final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";
    public final static String CORRECT_ON_FIRST_TRY = "correctOnFirstTry";
    public final static String NUMBER_OF_TASKS = "numberOfTasks";
    boolean answeredWrong = false;
    String classId, subjectId, jstring;
    JSONArray jsonArray;
    JSONObject jsonObject;
    int globalCounter;
    int correctAnswersInARow;
    int correctOnFirstTry;
    int numberOfTasks;
    DatabaseHelper myDb;
    Vibrator vibrator;

    String correctAnswer = "";
    String answer1 = "";
    String answer2 = "";
    String answer3 = "";
    String answer4 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        myDb = new DatabaseHelper(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Får jsonobjekt forrige aktivitet
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("jsonO"));
            jsonArray = jsonObject.getJSONArray("server_response");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Bundle extras = getIntent().getExtras();
        classId = extras.getString(CLASS_ID);
        subjectId = extras.getString(SUBJECT_ID);
        int taskId = extras.getInt(TASK_ID);
        correctAnswersInARow = extras.getInt(CORRECT_ANSWERS_IN_A_ROW);
        correctOnFirstTry = extras.getInt(CORRECT_ON_FIRST_TRY);
        numberOfTasks = extras.getInt(NUMBER_OF_TASKS);


        TextView class_view = (TextView) findViewById(R.id.fag);
        TextView subject_view = (TextView) findViewById(R.id.subject);
        TextView question_view = (TextView) findViewById(R.id.question);
        TextView button1 = (TextView) findViewById(R.id.button1);
        TextView button2 = (TextView) findViewById(R.id.button2);
        TextView button3 = (TextView) findViewById(R.id.button3);
        TextView button4 = (TextView) findViewById(R.id.button4);

        //Her kan vi hente ut neste spørsmål fra database med taskId
        if (nextTaskExists(jsonArray, taskId)) {
            List<String> task = nextTask(jsonArray, taskId);
            class_view.setText(classId);
            subject_view.setText(subjectId);
            question_view.setText(task.get(0));
            List<String> answers = randomizer(task.get(1), task.get(2), task.get(3), task.get(4));
            button1.setHint(answers.get(1));
            button2.setHint(answers.get(2));
            button3.setHint(answers.get(3));
            button4.setHint(answers.get(4));
            answer1 = answers.get(1);
            answer2 = answers.get(2);
            answer3 = answers.get(3);
            answer4 = answers.get(4);
            correctAnswer = answers.get(0);

        } else {
            subject_view.setText("Congratulations!");
            question_view.setText(correctOnFirstTry + "/" + numberOfTasks + " correct on first try");
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);

        }


        globalCounter = taskId;

    }
    
    //This method return true if there is a next task in the database
    //Otherwise it return false
    public boolean nextTaskExists(JSONArray jsonArray, int taskId) {

        if (taskId < jsonArray.length()) {
            return true;
        } else {
            return false;
        }

    }
    
    //Finds the given exercise from the database
    //Returns a list with all the info from the exercise
    public List<String> nextTask(JSONArray jsonArray, int taskId) {
        List<String> foo = new ArrayList<String>(Arrays.asList("f"));
        String exersices = "";
        try {
            JSONObject jo = jsonArray.getJSONObject(taskId);
            exersices = jo.getString("task");
            String[] taskArray = exersices.split("_");
            List<String> taskList = Arrays.asList(taskArray);
            return taskList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foo;

    }

    public void goToNextTask(View view) {

        Intent intent = new Intent(this, AssignmentActivity.class);
        Bundle extras = new Bundle();
        int taskId = globalCounter + 1;
        extras.putString(CLASS_ID, classId);
        extras.putString(SUBJECT_ID, subjectId);
        extras.putInt(TASK_ID, taskId);
        extras.putInt(CORRECT_ANSWERS_IN_A_ROW, correctAnswersInARow + 1);
        extras.putInt(CORRECT_ON_FIRST_TRY, correctOnFirstTry);
        extras.putInt(NUMBER_OF_TASKS, numberOfTasks + 1);
        extras.putString("jsonO", jsonObject.toString());
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void correctAnswerClicked() {
        if (!answeredWrong) {
            correctOnFirstTry++;
        }
        LinearLayout correctAnswerView = (LinearLayout) findViewById(R.id.correctAnswer);
        correctAnswerView.setVisibility(View.VISIBLE);

        if (correctAnswersInARow == 5 && !myDb.containsTrophy(2)) {
            addTrophy(2);
            Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
        }
        if (correctAnswersInARow == 10 && !myDb.containsTrophy(4)) {
            addTrophy(4);
            Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
        }
        if (correctAnswersInARow == 30 && !myDb.containsTrophy(10)) {
            addTrophy(10);
            Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
        }
    }

    public void wrongAnswerClicked() {
        answeredWrong = true;
        vibrator.vibrate(300);

        correctAnswersInARow = 1;
    }

    public void button1Clicked(View view) {
        if (answer1.equals(correctAnswer)) {
            correctAnswerClicked();
        } else {
            wrongAnswerClicked();
        }
    }

    public void button2Clicked(View view) {
        if (answer2.equals(correctAnswer)) {
            correctAnswerClicked();
        } else {
            wrongAnswerClicked();
        }
    }

    public void button3Clicked(View view) {
        if (answer3.equals(correctAnswer)) {

            correctAnswerClicked();
        } else {

            wrongAnswerClicked();
        }
    }

    public void button4Clicked(View view) {
        if (answer4.equals(correctAnswer)) {

            correctAnswerClicked();
        } else {

            wrongAnswerClicked();
        }
    }

    public void backToMain(View v) {
        Intent intent = new Intent(AssignmentActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /*This method takes four alternative answers as arguments. The first one is the correct answer
     to the exercise. The method returns a list with five string elements. The first element is the
     correct answer. The four next is all the alternatives in random order.*/
    public List<String> randomizer(String s1, String s2, String s3, String s4) {
        List<String> list = new ArrayList<>(Arrays.asList(s1, s2, s3, s4));
        List<String> randomList = new ArrayList<>(Arrays.asList(s1));
        Random rand = new Random();
        for (int i = 4; i > 0; i--) {
            randomList.add(list.remove(rand.nextInt(i)));
        }
        return randomList;
    }

    public void addTrophy(int trophyNumber) {
        myDb.insertData(trophyNumber);
    }

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