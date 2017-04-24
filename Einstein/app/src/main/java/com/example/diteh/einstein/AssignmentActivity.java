package com.example.diteh.einstein;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class AssignmentActivity extends AppCompatActivity {

    private final static String CLASS_ID = "classId";
    private final static String SUBJECT_ID = "subjectId";
    private final static String TASK_ID = "taskId";
    private final static String CORRECT_ANSWERS_IN_A_ROW = "correctAnswersInARow";
    private final static String CORRECT_ON_FIRST_TRY = "correctOnFirstTry";
    private final static String NUMBER_OF_TASKS = "numberOfTasks";
    protected boolean answeredWrong = false;
    private String classId, subjectId;
    protected JSONArray jsonArray;
    protected JSONObject jsonObject;
    protected int globalCounter,correctAnswersInARow,correctOnFirstTry, courseSubjectID;
    private int numberOfTasks;
    protected int[] myTrophies;
    protected int taskId;
    private Vibrator vibrator;
    protected String username, name, position;
    protected String correctAnswer = "";
    protected String answer1 = "";
    protected String answer2 = "";
    protected String answer3 = "";
    protected String answer4 = "";
    private int[] solved, assignID;
    protected int Asolved;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMyy");
    private Calendar c = Calendar.getInstance();
    private String mmyy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mmyy = dateFormat.format(c.getTime());
        Bundle extras = getIntent().getExtras();
        classId = extras.getString(CLASS_ID);
        subjectId = extras.getString(SUBJECT_ID);
        taskId = extras.getInt(TASK_ID);
        correctAnswersInARow = extras.getInt(CORRECT_ANSWERS_IN_A_ROW);
        correctOnFirstTry = extras.getInt(CORRECT_ON_FIRST_TRY);
        numberOfTasks = extras.getInt(NUMBER_OF_TASKS);
        name = extras.getString("name");
        username = extras.getString("username");
        solved = extras.getIntArray("solved");
        Asolved = extras.getInt("Asolved");
        position = extras.getString("position");
        myTrophies = new int[12];
        //make jsonobject and jsonarray
        try {

            jsonObject = new JSONObject(extras.getString("jsonO"));
            jsonArray = jsonObject.getJSONArray("assignments");

            JSONArray userdataArray = jsonObject.getJSONArray("userdata");
            JSONObject userdata = userdataArray.getJSONObject(0);
            courseSubjectID = userdata.getInt("courseSubjectID");
            JSONArray Optrophies = jsonObject.getJSONArray("trophy");

            //Make the trophy array, if no trophy from user make an empty array
            String ls = Optrophies.toString();
            if (!ls.equals("[]")) {
                for (int i = 0; i < Optrophies.length(); i++) {
                    JSONObject gettro = Optrophies.getJSONObject(i);
                    myTrophies[i] = gettro.getInt("trophynum");
                }
            }
            //get assignemtID to update assigment statistcs
            assignID = new int[jsonArray.length()];
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject getAssignID = jsonArray.getJSONObject(j);
                assignID[j] = getAssignID.getInt("assignID");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myTrophies = new int[1];
            myTrophies[0] = 0;
        }

        //find buttons on display
        TextView class_view = (TextView) findViewById(R.id.fag);
        TextView subject_view = (TextView) findViewById(R.id.subject);
        TextView question_view = (TextView) findViewById(R.id.question);
        TextView button1 = (TextView) findViewById(R.id.button1);
        TextView button2 = (TextView) findViewById(R.id.button2);
        TextView button3 = (TextView) findViewById(R.id.button3);
        TextView button4 = (TextView) findViewById(R.id.button4);

        //Get nect task out to display
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
            //if all assigments are solved do this
            subject_view.setText(getMessage(correctOnFirstTry, numberOfTasks));
            if (numberOfTasks != 0) {
                question_view.setText(correctOnFirstTry + "/" + numberOfTasks + " correct on first try.");
            }
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);

            new Background(0, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
        }
        globalCounter = taskId;
    }

    //This method decides which message that should be shown to the user depending on
    //how many of the tasks that were correct on first try
    public String getMessage(int correctOnFirstTry, int numberOfTasks) {
        if (numberOfTasks == 0) {
            return "No exercises available.";
        }
        float percentScore = (float) correctOnFirstTry / numberOfTasks;
        if (percentScore < 0.6) {
            return "Better luck next time.";
        } else {
            return "Congratulations!";
        }
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
            String[] temp1 = taskArray;
            List<String> taskList = Arrays.asList(taskArray);
            List<String> temp = taskList;
            return taskList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foo;
    }

    //prepare and launch next assignment
    public void goToNextTask(View view) {
        Intent intent = new Intent(this, AssignmentActivity.class);
        Bundle extras = new Bundle();
        extras.putString(CLASS_ID, classId);
        extras.putString(SUBJECT_ID, subjectId);
        extras.putInt(TASK_ID, taskId);
        extras.putInt(CORRECT_ANSWERS_IN_A_ROW, correctAnswersInARow);
        extras.putInt(CORRECT_ON_FIRST_TRY, correctOnFirstTry);
        extras.putInt(NUMBER_OF_TASKS, numberOfTasks);
        extras.putString("jsonO", jsonObject.toString());
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putIntArray("solved", solved);
        extras.putInt("Asolved", Asolved);
        extras.putString("position", position);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }

    //This method checks if the the right answer is selected, and see if a new trophy has been earned
    // if new trophy, update database. Only students can get trophies
    public void correctAnswerClicked() {
        if (!answeredWrong) {
            correctOnFirstTry++;
            correctAnswersInARow++;
            solved[taskId] = 1;
            taskId = globalCounter + 1;
            Asolved++;
            Toast.makeText(AssignmentActivity.this, "Winning streak is on " + correctAnswersInARow, Toast.LENGTH_LONG).show();
            Button bt1 = (Button) findViewById(R.id.button1);
            Button bt2 = (Button) findViewById(R.id.button2);
            Button bt3 = (Button) findViewById(R.id.button3);
            Button bt4 = (Button) findViewById(R.id.button4);
            bt1.setEnabled(false);
            bt2.setEnabled(false);
            bt3.setEnabled(false);
            bt4.setEnabled(false);
        }
        LinearLayout correctAnswerView = (LinearLayout) findViewById(R.id.correctAnswer);
        correctAnswerView.setVisibility(View.VISIBLE);

        if (position.equals("Student")) {
            if (correctAnswersInARow == 1 && findTrophy(1)) {
                new Background(1, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(1);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (correctAnswersInARow == 5 && findTrophy(2)) {
                new Background(2, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(2);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (correctAnswersInARow == 10 && findTrophy(3)) {
                new Background(3, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(3);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 5 && findTrophy(7)) {
                new Background(7, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(7);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 10 && findTrophy(8)) {
                new Background(8, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(8);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 50 && findTrophy(9)) {
                new Background(9, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(9);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 100 && findTrophy(10)) {
                new Background(10, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(10);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 500 && findTrophy(11)) {
                new Background(11, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(11);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
            if (Asolved == 1000 && findTrophy(12)) {
                new Background(12, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
                tempTrophyArray(12);
                Toast.makeText(this, "Congrats! New trophy in the Trophy Room!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //This method checks if the wrong answer is selected
    public void wrongAnswerClicked() {
        answeredWrong = true;
        vibrator.vibrate(300);
        correctAnswersInARow = 0;
        Toast.makeText(this, "To bad, winning streak reset to " + correctAnswersInARow, Toast.LENGTH_LONG).show();
    }

    //next four methods checks if the clicked button contains the right or wrong answer
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

    //push back button on screen
    public void backToMain(View v) {
        Intent intent;
        if (position.equals("Student")) {
            intent = new Intent(AssignmentActivity.this, MainActivity.class);
        } else {
            intent = new Intent(AssignmentActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        new Background(0, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
        startActivity(intent);
        finish();
    }

    //use anndroid back button
    @Override
    public void onBackPressed() {
        Intent intent;
        if (position.equals("Student")) {
            intent = new Intent(AssignmentActivity.this, MainActivity.class);
        } else {
            intent = new Intent(AssignmentActivity.this, TeachingActivity.class);
        }
        Bundle extras = new Bundle();
        extras.putString("name", name);
        extras.putString("username", username);
        extras.putString("position", position);
        intent.putExtras(extras);
        new Background(0, courseSubjectID, correctAnswersInARow, correctOnFirstTry, taskId, username, numberOfTasks, assignID, solved, mmyy).execute();
        startActivity(intent);
        finish();
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

    //This method checks if the trophy has already been earned, takes in a trophynumber and returns
    //a true if the trophy is not found.
    public boolean findTrophy(int trophynumber) {
        for (int i = 0; i < myTrophies.length; i++) {
            if (myTrophies[i] == trophynumber) {
                return false;
            }
        }
        return true;
    }

    //add trophy to a temporary array for user trophies
    public void tempTrophyArray(int trophynum) {
        for (int i = 0; i < myTrophies.length; i++) {
            if (myTrophies[i] == 0) {
                myTrophies[i] = trophynum;
                break;
            }
        }
    }

    //this saves userinformation to the database
    class Background extends AsyncTask<Void, Void, String> {
        //Gets data from database
        String username, mmyy;
        String JSON_STRING, js_string;
        String json_url;
        int ansInARow, taskID, correctOnFirstTry, courseSubjectID, trophynum, numberOfTask;
        int[] assignID, solved;

        //get input data
        public Background(int trophynum, int courseSubjectID, int ansInARow, int correctOnFirstTry, int taskID, String username, int numberOfTask, int[] assignID, int[] solved, String mmyy) {
            this.trophynum = trophynum;
            this.courseSubjectID = courseSubjectID;
            this.correctOnFirstTry = correctOnFirstTry;
            this.taskID = taskID;
            this.ansInARow = ansInARow;
            this.username = username;
            this.numberOfTask = numberOfTask;
            this.assignID = assignID;
            this.solved = solved;
            this.mmyy = mmyy;
        }

        @Override
        protected void onPreExecute() {
            //reset assignments so user can start over
            if (taskID == numberOfTask) {
                taskID = 0;
                correctOnFirstTry = 0;
            }

            String Ssolved = "", SassignID = "";
            for (int i = 0; i < solved.length; i++) {
                Ssolved = "" + Ssolved + "," + solved[i];
                SassignID = "" + SassignID + "," + assignID[i];

            }

            //url for php that fetch data from database, comes back as json object
            json_url = "https://truongtrxu.000webhostapp.com/updateUserProgress1.php?courseSubjectID=" + courseSubjectID + "&username=" + username
                    + "&trophynum=" + trophynum + "&taskID=" + taskID + "&ansInARow=" + ansInARow + "&correctOnFirstTry=" + correctOnFirstTry + "&assignID=" + SassignID + "&solved=" + Ssolved
                    + "&mmyy=" + mmyy;
        }

        @Override
        protected String doInBackground(Void... params) {
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

        }
    }
}

