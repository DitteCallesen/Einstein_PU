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

    public final static String CLASS_ID = "com.example.myfirstapp.CLASS_ID";
    public final static String SUBJECT_ID = "com.example.myfirstapp.SUBJECT_ID";
    public final static String TASK_ID = "com.example.myfirstapp.COUNTER";

    int class_id;
    int subject_id;
    int globalCounter;
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
            answer1 = answers.get(1);
            answer2 = answers.get(2);
            answer3 = answers.get(3);
            answer4 = answers.get(4);
            correctAnswer = answers.get(0);
        }
        else {
            // Her må det komme en beskjed om at det er tomt for oppgaver
            subject_view.setText("false");
            question_view.setText("false");
            button1.setText("false");
            button2.setText("false");
            button3.setText("false");
            button4.setText("false");
        }


        globalCounter = task_id;

    }

    public void correctAnswerClicked() {
        Intent intent = new Intent(this, AssignmentActivity.class);
        Bundle extras = new Bundle();
        int task_id = globalCounter + 1;
        extras.putInt(CLASS_ID, 0);
        extras.putInt(SUBJECT_ID, 0);
        extras.putInt(TASK_ID, task_id);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void wrongAnswerClicked() {
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