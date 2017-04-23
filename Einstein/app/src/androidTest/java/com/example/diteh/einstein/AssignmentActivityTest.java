package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mariasoleim on 27.03.2017.
 */

public class AssignmentActivityTest {

    @Rule
    public ActivityTestRule<AssignmentActivity> assignmentActivityTestRule = new ActivityTestRule<AssignmentActivity>(AssignmentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, AssignmentActivity.class);
            Bundle extras = new Bundle();
            extras.putString("jsonO", "{\"userdata\":[{\"username\":\"a\",\"taskID\":\"10\",\"courseSubjectID\":\"1\",\"ansInARow\":\"7\",\"Asolved\":\"0\",\"correctOnFirstTry\":\"10\"}],\"assignments\":[{\"task\":\"x + 4 = 6_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"1\",\"assignID\":\"1\"},{\"task\":\"4 + x = 8_x = 4_x = 5_x = 6_x = 7\",\"difficulty\":\"1\",\"assignID\":\"3\"},{\"task\":\"-8 = 2 - x_x = 10_x = 12_x = 14_x = 16\",\"difficulty\":\"1\",\"assignID\":\"4\"},{\"task\":\"8 - x = 7_x = 1_x = 2_x=3_x=4\",\"difficulty\":\"1\",\"assignID\":\"5\"},{\"task\":\"5 + x = 21_x = 16_x = 21_x = 1_x = 5_x = 3\",\"difficulty\":\"1\",\"assignID\":\"6\"},{\"task\":\"x - 3 = 4_x = 7_x = 4_x = 6_x = 11\",\"difficulty\":\"1\",\"assignID\":\"7\"},{\"task\":\"8 = x - 5_x = 13_x = 5_x = 10_x = 12_x = 8\",\"difficulty\":\"1\",\"assignID\":\"8\"},{\"task\":\"4x + 3x = 14_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"2\",\"assignID\":\"9\"},{\"task\":\"12 + x = 5x_x = 3_ x = 4_ x = 5_ x = 6\",\"difficulty\":\"2\",\"assignID\":\"10\"},{\"task\":\"8x + 18 = 26x_x = 1_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"11\"},{\"task\":\"2x*4 = 2x + 12_x = 2_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"12\"},{\"task\":\"4 - 2x = 2*2x + 1_x = 2_x = 1_x = -1_x = 3\",\"difficulty\":\"2\",\"assignID\":\"13\"},{\"task\":\"x + 15 = 36_x = 21_x = 13_x = 14_x = 15\",\"difficulty\":\"1\",\"assignID\":\"14\"}],\"trophy\":[{\"trophynum\":\"1\"},{\"trophynum\":\"6\"},{\"trophynum\":\"4\"},{\"trophynum\":\"2\"}]}");
            extras.putString("username", "Test2");
            extras.putString("name","ForBrukerTest2");
            extras.putString("position", "Student");

            extras.putString("classId", "Mathematics");
            extras.putString("subjectId", "Algebra");
            extras.putInt("taskId", 0);
            extras.putInt("correctAnswersInARow", 9);
            extras.putInt("correctOnFirstTry", 10);
            extras.putInt("numberOfTasks", 13);
            extras.putIntArray("solved", new int[13]);
            extras.putInt("Asolved", 9);
            result.putExtras(extras);

            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorT = getInstrumentation().addMonitor(TeachingActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorA = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    private AssignmentActivity assignmentActivity = null;

    @Before
    public void setUp() throws Exception {
        assignmentActivity = assignmentActivityTestRule.getActivity();
    }
    @Test
    public void testLaunchOfMainActivityOnBackToMainButtonClick(){

        //go to back to main menu, first to teachingsite
        assignmentActivity.backToMain(null);

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);

        //test to go to main for students
        assignmentActivity.position="Admin";
        assignmentActivity.backToMain(null);
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorT, 5000);
        assertNotNull(nextActivity);

    }


    @Test
    public void getMessage() throws Exception {
        int input1;
        int input2;
        String output;
        String expected;

        input1 = 5;
        input2 = 10;
        expected = "Better luck next time.";
        output = assignmentActivity.getMessage(input1, input2);
        assertEquals(expected, output);

        input1 = 5;
        input2 = 0;
        expected = "No exercises available.";
        output = assignmentActivity.getMessage(input1, input2);
        assertEquals(expected, output);

        input1 = 6;
        input2 = 10;
        expected = "Congratulations!";
        output = assignmentActivity.getMessage(input1, input2);
        assertEquals(expected, output);
    }

    @Test
    public void nextTaskExists() throws Exception {
        boolean output;
        output = assignmentActivity.nextTaskExists(assignmentActivity.jsonArray,assignmentActivity.taskId);
        assertEquals(true, output);

        output = assignmentActivity.nextTaskExists(assignmentActivity.jsonArray,20);
        assertEquals(false, output);

    }

    @Test
    public void nextTask() throws Exception {
        //check first assignment
        List<String> expectedList = new ArrayList<String>(Arrays.asList("x + 4 = 6","x = 2","x = 3","x = 4","x = 5"));
        List<String> output = assignmentActivity.nextTask(assignmentActivity.jsonArray,0);
        assertEquals(expectedList,output);

        //if index is > array length
        List<String> foo = new ArrayList<String>(Arrays.asList("f"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray,20);
        assertEquals(foo,output);

        //check last assignment
        expectedList = new ArrayList<String>(Arrays.asList("x + 15 = 36","x = 21","x = 13","x = 14","x = 15"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray,12);
        assertEquals(expectedList,output);

        //check middel assignment
        expectedList = new ArrayList<String>(Arrays.asList("x - 3 = 4","x = 7","x = 4","x = 6","x = 11"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray,5);
        assertEquals(expectedList,output);


    }

    @Test
    public void goToNextTask() throws Exception {
        int tempAsolved = assignmentActivity.Asolved;
        int tempTaskID = assignmentActivity.taskId;

        //test if new activity has the right information from the previous activity
        assignmentActivity.goToNextTask(null);
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitorA,5000);
        Bundle extras = activity.getIntent().getExtras();
        int RAsloved, RTaskid;
        RAsloved=extras.getInt("Asolved");
        RTaskid=extras.getInt("taskId");
        assertEquals(tempAsolved,RAsloved);
        assertEquals(tempTaskID,RTaskid);
    }

    @Test
    public void testcorrectAnswerClicked() throws Exception {
        final int corrOnFtry, corrARow, solvedTask, TId, AnSolved;
        corrOnFtry = assignmentActivity.correctOnFirstTry;
        corrARow=assignmentActivity.correctAnswersInARow;
        solvedTask=0;
        assignmentActivity.taskId=11;
        TId=assignmentActivity.taskId;
        assignmentActivity.globalCounter=TId;
        AnSolved=assignmentActivity.Asolved;
        assignmentActivity.answeredWrong =false;
        int[] tro= assignmentActivity.myTrophies;


        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();
        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout Popup = (LinearLayout) act2.findViewById(R.id.correctAnswer);
                assertEquals(View.INVISIBLE,Popup.getVisibility());

                //trophy three is not earned
                boolean checkTrophyTen=false;
                for(int i=0;i<act2.myTrophies.length;i++){
                    if(act2.myTrophies[i]==3){
                        checkTrophyTen=true;
                        break;
                    }
                }
                assertEquals(false,checkTrophyTen);

                act2.correctAnswerClicked();

                assertEquals(corrOnFtry+1,act2.correctOnFirstTry);
                assertEquals(corrARow+1,act2.correctAnswersInARow);

                Button bt1 = (Button) act2.findViewById(R.id.button1);
                Button bt2 = (Button) act2.findViewById(R.id.button2);
                Button bt3 = (Button) act2.findViewById(R.id.button3);
                Button bt4 = (Button) act2.findViewById(R.id.button4);

                assertEquals(false,bt1.isEnabled());
                assertEquals(false,bt2.isEnabled());
                assertEquals(false,bt3.isEnabled());
                assertEquals(false,bt4.isEnabled());


                assertEquals(View.VISIBLE,Popup.getVisibility());

                //trophy three is earned since ansInARow is 10
                for(int i=0;i<act2.myTrophies.length;i++){
                    if(act2.myTrophies[i]==3){
                        checkTrophyTen=true;
                        break;
                    }
                }
                assertEquals(true,checkTrophyTen);
            }
        });







    }

    @Test
    public void wrongAnswerClicked() throws Exception {

        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertNotEquals(act2.correctAnswersInARow,0);
                act2.wrongAnswerClicked();
                assertEquals(0,act2.correctAnswersInARow);
            }
        });


    }

    @Test
    public void button1Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0,act2.correctAnswersInARow);

                act2.correctAnswer="Corr";
                act2.answer1="somethin";

                act2.button1Clicked(null);
                assertEquals(0,act2.correctAnswersInARow);
                act2.answer1="Corr";
                act2.answeredWrong=false;
                act2.button1Clicked(null);
                assertEquals(solved+1,act2.Asolved);
                assertEquals(1,act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void button2Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0,act2.correctAnswersInARow);

                act2.correctAnswer="Corr";
                act2.answer2="somethin";

                act2.button2Clicked(null);
                assertEquals(0,act2.correctAnswersInARow);
                act2.answer2="Corr";
                act2.answeredWrong=false;
                act2.button2Clicked(null);
                assertEquals(solved+1,act2.Asolved);
                assertEquals(1,act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void button3Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0,act2.correctAnswersInARow);

                act2.correctAnswer="Corr";
                act2.answer3="somethin";

                act2.button3Clicked(null);
                assertEquals(0,act2.correctAnswersInARow);
                act2.answer3="Corr";
                act2.answeredWrong=false;
                act2.button3Clicked(null);
                assertEquals(solved+1,act2.Asolved);
                assertEquals(1,act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void button4Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0,act2.correctAnswersInARow);

                act2.correctAnswer="Corr";
                act2.answer4="somethin";

                act2.button4Clicked(null);
                assertEquals(0,act2.correctAnswersInARow);
                act2.answer4="Corr";
                act2.answeredWrong=false;
                act2.button4Clicked(null);
                assertEquals(solved+1,act2.Asolved);
                assertEquals(1,act2.correctAnswersInARow);
            }
        });
    }


    @Test
    public void randomizer() throws Exception {
        String s1="ok1",s2="ok2",s3="ok3",s4="ok4";
        List<String> InOrderList= new ArrayList<>(Arrays.asList(s1,s1,s2,s3,s4));

        //one randomized list might be equal to the InOrderList, do three incase
        List<String> FirstList=assignmentActivity.randomizer(s1,s2,s3,s4);
        List<String> SecondtList=assignmentActivity.randomizer(s1,s2,s3,s4);
        List<String> ThirdList=assignmentActivity.randomizer(s1,s2,s3,s4);
        //assumes the function work, if it dont work, it means all the three randomized list are equal to InOrderList
        boolean success =true;
        if(InOrderList.equals(FirstList)&&InOrderList.equals(SecondtList)&&InOrderList.equals(ThirdList)){
            success=false;
        }
        assertEquals(true,success);

    }

    @Test
    public void tempTrophyArray() throws Exception {
        // has trophy 1,2,4,6
        int[] tempTrophies = assignmentActivity.myTrophies;
        //insert trophy
        assignmentActivity.tempTrophyArray(3);
        boolean foundthree=false,foundfour=false,foundten=false;


        for (int i=0;i<assignmentActivity.myTrophies.length;i++){
            if(assignmentActivity.myTrophies[i]==3){
                foundthree=true;
            }
            if(assignmentActivity.myTrophies[i]==4){
                foundfour=true;
            }
            if(assignmentActivity.myTrophies[i]==10){
                foundten=true;
            }
        }
        assertEquals(true,foundthree);
        assertEquals(true,foundfour);
        assertEquals(false,foundten);
    }

    @Test
    public void findTrophy() throws Exception {
            // has trophy 1,2,4,6
            // dont have trophy 3,5,7,8,9,10,11,12
            //will return true if the trophy is not found
        assertEquals(true,assignmentActivity.findTrophy(3));
        assertEquals(true,assignmentActivity.findTrophy(5));
        assertEquals(true,assignmentActivity.findTrophy(7));
        assertEquals(true,assignmentActivity.findTrophy(8));
        assertEquals(true,assignmentActivity.findTrophy(9));
        assertEquals(true,assignmentActivity.findTrophy(10));
        assertEquals(true,assignmentActivity.findTrophy(11));
        assertEquals(true,assignmentActivity.findTrophy(12));
        assertEquals(true,assignmentActivity.findTrophy(13));

        assertEquals(false,assignmentActivity.findTrophy(1));
        assertEquals(false,assignmentActivity.findTrophy(2));
        assertEquals(false,assignmentActivity.findTrophy(4));
        assertEquals(false,assignmentActivity.findTrophy(6));

    }



    @After
    public void tearDown() throws Exception {
        assignmentActivity = null;
    }


}