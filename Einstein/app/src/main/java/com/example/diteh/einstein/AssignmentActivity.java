package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Random.*;

public class AssignmentActivity extends AppCompatActivity {

    public final static String CLASS_ID = "class_id";
    public final static String SUBJECT_ID = "subject_id";
    public final static String TASK_ID = "task_id";

    int class_id;
    int subject_id;
    int globalCounter;
    public static int counterC=0;
    public static int counterW=0;

    String correctAnswer = "";
    String answer1 = "";
    String answer2 = "";
    String answer3 = "";
    String answer4 = "";

    //Denne metoden avgjør om det finnes en oppgave til i databasen
    public boolean nextTaskExists(int class_id, int subject_id, int task_id) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("tasks_db.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] task_array = mLine.split("_");
                List<String> task_list = Arrays.asList(task_array);
                if (Integer.parseInt(task_list.get(0)) == class_id
                        && Integer.parseInt(task_list.get(1)) == subject_id
                        && Integer.parseInt(task_list.get(2)) == task_id) {
                    return true;
                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return false;
    }

    //Finner riktig oppgave i databasen
    //Returnerer med en liste med all info om oppgaven
    public List<String> nextTask(int class_id, int subject_id, int task_id) {
        List<String> foo = new ArrayList<String>(Arrays.asList("f", "f", "f", "f", "f", "f", "f"));
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("tasks_db.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] task_array = mLine.split("_");
                List<String> task_list = Arrays.asList(task_array);
                if (Integer.parseInt(task_list.get(0)) == class_id
                        && Integer.parseInt(task_list.get(1)) == subject_id
                        && Integer.parseInt(task_list.get(2)) == task_id) {
                    return task_list;
                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return foo;
                }
            }
        }
        return foo;
    }

    public String getClassName(String id) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("class.txt")));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] class_array = mLine.split("_");
                List<String> class_list = Arrays.asList(class_array);
                if ( class_list.get(0).equals(id)) {
                    return  class_list.get(1);
                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return "false";
                }
            }
        }
        return "false";
    }

    public String getSubjectName(String class_id, String subject_id) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("subject.txt")));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] subject_array = mLine.split("_");
                List<String> subject_list = Arrays.asList(subject_array);
                if (subject_list.get(0).equals(class_id) && subject_list.get(1).equals(subject_id)) {
                    return subject_list.get(2);
                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return "false";
                }
            }
        }
        return "false";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        Bundle extras = getIntent().getExtras();
        class_id = extras.getInt(CLASS_ID);
        subject_id = extras.getInt(SUBJECT_ID);
        int task_id = extras.getInt(TASK_ID);

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
        if (nextTaskExists(class_id, subject_id, task_id)) {
            List<String> task = nextTask(class_id, subject_id, task_id);
            class_view.setText(getClassName(task.get(0)));
            subject_view.setText(getSubjectName(task.get(0), task.get(1)));
            question_view.setText(task.get(4));
            List<String> answers = randomizer(task.get(5), task.get(6), task.get(7), task.get(8));
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

    public void correctAnswerClicked() {
        counterC++;
        Intent intent = new Intent(this, AssignmentActivity.class);
        Bundle extras = new Bundle();
        int task_id = globalCounter + 1;
        extras.putInt(CLASS_ID, class_id);
        extras.putInt(SUBJECT_ID, subject_id);
        extras.putInt(TASK_ID, task_id);
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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