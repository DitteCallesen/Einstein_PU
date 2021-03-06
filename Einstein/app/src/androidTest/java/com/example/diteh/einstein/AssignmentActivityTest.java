package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mariasoleim on 27.03.2017.
 */

public class AssignmentActivityTest {
    //Initiating Assigment activity for test
    @Rule
    public ActivityTestRule<AssignmentActivity> assignmentActivityTestRule = new ActivityTestRule<AssignmentActivity>(AssignmentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, AssignmentActivity.class);
            Bundle extras = new Bundle();
            extras.putString("jsonO", "{\"userdata\":[{\"username\":\"a\",\"taskID\":\"10\",\"courseSubjectID\":\"1\",\"ansInARow\":\"7\",\"Asolved\":\"0\",\"correctOnFirstTry\":\"10\"}],\"assignments\":[{\"task\":\"x + 4 = 6_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"1\",\"assignID\":\"1\"},{\"task\":\"4 + x = 8_x = 4_x = 5_x = 6_x = 7\",\"difficulty\":\"1\",\"assignID\":\"3\"},{\"task\":\"-8 = 2 - x_x = 10_x = 12_x = 14_x = 16\",\"difficulty\":\"1\",\"assignID\":\"4\"},{\"task\":\"8 - x = 7_x = 1_x = 2_x=3_x=4\",\"difficulty\":\"1\",\"assignID\":\"5\"},{\"task\":\"5 + x = 21_x = 16_x = 21_x = 1_x = 5_x = 3\",\"difficulty\":\"1\",\"assignID\":\"6\"},{\"task\":\"x - 3 = 4_x = 7_x = 4_x = 6_x = 11\",\"difficulty\":\"1\",\"assignID\":\"7\"},{\"task\":\"8 = x - 5_x = 13_x = 5_x = 10_x = 12_x = 8\",\"difficulty\":\"1\",\"assignID\":\"8\"},{\"task\":\"4x + 3x = 14_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"2\",\"assignID\":\"9\"},{\"task\":\"12 + x = 5x_x = 3_ x = 4_ x = 5_ x = 6\",\"difficulty\":\"2\",\"assignID\":\"10\"},{\"task\":\"8x + 18 = 26x_x = 1_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"11\"},{\"task\":\"2x*4 = 2x + 12_x = 2_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"12\"},{\"task\":\"4 - 2x = 2*2x + 1_x = 2_x = 1_x = -1_x = 3\",\"difficulty\":\"2\",\"assignID\":\"13\"},{\"task\":\"x + 15 = 36_x = 21_x = 13_x = 14_x = 15\",\"difficulty\":\"1\",\"assignID\":\"14\"}],\"trophy\":[{\"trophynum\":\"1\"},{\"trophynum\":\"6\"},{\"trophynum\":\"4\"},{\"trophynum\":\"2\"}]}");
            extras.putString("username", "Test2");
            extras.putString("name", "ForBrukerTest2");
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

    //monitor used to see other activities being initiated or not
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorT = getInstrumentation().addMonitor(TeachingActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorA = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    private AssignmentActivity assignmentActivity = null;

    @Before
    public void setUp() throws Exception {
        assignmentActivity = assignmentActivityTestRule.getActivity();
    }

    //test method backToMain
    @Test
    public void testLaunchOfMainActivityOnBackToMainButtonClick() {

        //go to back to main menu, first to teachingsite
        assignmentActivity.backToMain(null);

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);

        //test to go to main for students
        assignmentActivity.position = "Admin";
        assignmentActivity.backToMain(null);
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorT, 5000);
        assertNotNull(nextActivity);

    }

    //test method onBackPressed
    @Test
    public void testOnBackPressed() {

        //go to back to main menu, first to teachingsite
        assignmentActivity.onBackPressed();

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);

        //test to go to main for students
        assignmentActivity.position = "Admin";
        assignmentActivity.onBackPressed();
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorT, 5000);
        assertNotNull(nextActivity);

    }

    //test the getMessage method
    @Test
    public void testGetMessage() throws Exception {
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
    public void testNextTaskExists() throws Exception {
        boolean output;
        //if taskId < assigment array, next task exist
        output = assignmentActivity.nextTaskExists(assignmentActivity.jsonArray, assignmentActivity.taskId);
        assertEquals(true, output);

        //since 20> assigment array length, next task does not exist
        output = assignmentActivity.nextTaskExists(assignmentActivity.jsonArray, 20);
        assertEquals(false, output);

    }

    @Test
    public void testNextTask() throws Exception {
        //check first assignment
        List<String> expectedList = new ArrayList<String>(Arrays.asList("x + 4 = 6", "x = 2", "x = 3", "x = 4", "x = 5"));
        List<String> output = assignmentActivity.nextTask(assignmentActivity.jsonArray, 0);
        assertEquals(expectedList, output);

        //if index is > array length
        List<String> foo = new ArrayList<String>(Arrays.asList("f"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray, 20);
        assertEquals(foo, output);

        //check last assignment
        expectedList = new ArrayList<String>(Arrays.asList("x + 15 = 36", "x = 21", "x = 13", "x = 14", "x = 15"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray, 12);
        assertEquals(expectedList, output);

        //check middel assignment
        expectedList = new ArrayList<String>(Arrays.asList("x - 3 = 4", "x = 7", "x = 4", "x = 6", "x = 11"));
        output = assignmentActivity.nextTask(assignmentActivity.jsonArray, 5);
        assertEquals(expectedList, output);


    }


    @Test
    public void testGoToNextTask() throws Exception {
        int tempAsolved = assignmentActivity.Asolved;
        int tempTaskID = assignmentActivity.taskId;

        //test if new activity has the right information from the previous activity
        assignmentActivity.goToNextTask(null);
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitorA, 5000);
        Bundle extras = activity.getIntent().getExtras();
        int RAsloved, RTaskid;
        RAsloved = extras.getInt("Asolved");
        RTaskid = extras.getInt("taskId");
        assertEquals(tempAsolved, RAsloved);
        assertEquals(tempTaskID, RTaskid);
    }

    @Test
    public void testCorrectAnswerClicked() throws Exception {
        final int corrOnFtry, corrARow, TId;
        assignmentActivity.correctAnswersInARow=0;
        corrOnFtry = assignmentActivity.correctOnFirstTry;
        corrARow = assignmentActivity.correctAnswersInARow;
        assignmentActivity.taskId = 11;
        TId = assignmentActivity.taskId;
        assignmentActivity.globalCounter = TId;
        assignmentActivity.answeredWrong = false;
        assignmentActivity.Asolved=0;
        assignmentActivity.correctAnswersInARow=0;
        assignmentActivity.myTrophies=new int[12];

        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();
        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout Popup = (LinearLayout) act2.findViewById(R.id.correctAnswer);
                assertEquals(View.INVISIBLE, Popup.getVisibility());

                //first trophy
                act2.correctAnswerClicked();

                assertEquals(corrOnFtry + 1, act2.correctOnFirstTry);
                assertEquals(corrARow + 1, act2.correctAnswersInARow);

                Button bt1 = (Button) act2.findViewById(R.id.button1);
                Button bt2 = (Button) act2.findViewById(R.id.button2);
                Button bt3 = (Button) act2.findViewById(R.id.button3);
                Button bt4 = (Button) act2.findViewById(R.id.button4);

                assertEquals(false, bt1.isEnabled());
                assertEquals(false, bt2.isEnabled());
                assertEquals(false, bt3.isEnabled());
                assertEquals(false, bt4.isEnabled());


                assertEquals(View.VISIBLE, Popup.getVisibility());

                //see that trophy number 1 is in mytrophies
                assertEquals(1, act2.myTrophies[0]);

                //second trophy
                act2.correctAnswersInARow=4;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 2, act2.correctOnFirstTry);
                assertEquals(5, act2.correctAnswersInARow);
                assertEquals(2, act2.myTrophies[1]);

                //third trophy
                act2.correctAnswersInARow=9;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 3, act2.correctOnFirstTry);
                assertEquals(10, act2.correctAnswersInARow);
                assertEquals(3, act2.myTrophies[2]);

                //seventh trophy
                act2.Asolved=4;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 4, act2.correctOnFirstTry);
                assertEquals(5, act2.Asolved);
                assertEquals(7, act2.myTrophies[3]);

                //eigth trophy
                act2.Asolved=9;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 5, act2.correctOnFirstTry);
                assertEquals(10, act2.Asolved);
                assertEquals(8, act2.myTrophies[4]);

                //ninth trophy
                act2.Asolved=49;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 6, act2.correctOnFirstTry);
                assertEquals(50, act2.Asolved);
                assertEquals(9, act2.myTrophies[5]);

                //tenth trophy
                act2.Asolved=99;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 7, act2.correctOnFirstTry);
                assertEquals(100, act2.Asolved);
                assertEquals(10, act2.myTrophies[6]);

                //eleventh trophy
                act2.Asolved=499;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 8, act2.correctOnFirstTry);
                assertEquals(500, act2.Asolved);
                assertEquals(11, act2.myTrophies[7]);

                //twelveth trophy
                act2.Asolved=999;
                act2.correctAnswerClicked();
                assertEquals(corrOnFtry + 9, act2.correctOnFirstTry);
                assertEquals(1000, act2.Asolved);
                assertEquals(12, act2.myTrophies[8]);
            }
        });
    }

    //check if the method wrongAnswerClick changes the variable correctAnswersInARow
    @Test
    public void testWrongAnswerClicked() throws Exception {

        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //first confirm correctAnswersInARow not zero
                assertNotEquals(act2.correctAnswersInARow, 0);
                act2.wrongAnswerClicked();
                //confirm correctAnswersInARow is zero
                assertEquals(0, act2.correctAnswersInARow);
            }
        });


    }

    @Test
    public void testButton1Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0, act2.correctAnswersInARow);

                act2.correctAnswer = "Corr";
                act2.answer1 = "somethin";

                act2.button1Clicked(null);
                assertEquals(0, act2.correctAnswersInARow);
                act2.answer1 = "Corr";
                act2.answeredWrong = false;
                act2.button1Clicked(null);
                assertEquals(solved + 1, act2.Asolved);
                assertEquals(1, act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void testButton2Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0, act2.correctAnswersInARow);

                act2.correctAnswer = "Corr";
                act2.answer2 = "somethin";

                act2.button2Clicked(null);
                assertEquals(0, act2.correctAnswersInARow);
                act2.answer2 = "Corr";
                act2.answeredWrong = false;
                act2.button2Clicked(null);
                assertEquals(solved + 1, act2.Asolved);
                assertEquals(1, act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void testButton3Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0, act2.correctAnswersInARow);

                act2.correctAnswer = "Corr";
                act2.answer3 = "somethin";

                act2.button3Clicked(null);
                assertEquals(0, act2.correctAnswersInARow);
                act2.answer3 = "Corr";
                act2.answeredWrong = false;
                act2.button3Clicked(null);
                assertEquals(solved + 1, act2.Asolved);
                assertEquals(1, act2.correctAnswersInARow);
            }
        });
    }

    @Test
    public void testButton4Clicked() throws Exception {
        final AssignmentActivity act2 = assignmentActivityTestRule.getActivity();

        act2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int solved = act2.Asolved;

                assertNotEquals(0, act2.correctAnswersInARow);

                act2.correctAnswer = "Corr";
                act2.answer4 = "somethin";

                act2.button4Clicked(null);
                assertEquals(0, act2.correctAnswersInARow);
                act2.answer4 = "Corr";
                act2.answeredWrong = false;
                act2.button4Clicked(null);
                assertEquals(solved + 1, act2.Asolved);
                assertEquals(1, act2.correctAnswersInARow);
            }
        });
    }


    @Test
    public void testRandomizer() throws Exception {
        String s1 = "ok1", s2 = "ok2", s3 = "ok3", s4 = "ok4";
        List<String> InOrderList = new ArrayList<>(Arrays.asList(s1, s1, s2, s3, s4));

        //one randomized list might be equal to the InOrderList, do three incase
        List<String> FirstList = assignmentActivity.randomizer(s1, s2, s3, s4);
        List<String> SecondtList = assignmentActivity.randomizer(s1, s2, s3, s4);
        List<String> ThirdList = assignmentActivity.randomizer(s1, s2, s3, s4);
        //assumes the function work, if it dont work, it means all the three randomized list are equal to InOrderList
        boolean success = true;
        if (InOrderList.equals(FirstList) && InOrderList.equals(SecondtList) && InOrderList.equals(ThirdList)) {
            success = false;
        }
        assertEquals(true, success);

    }

    @Test
    public void testTempTrophyArray() throws Exception {
        // has trophy 1,2,4,6
        int[] tempTrophies = assignmentActivity.myTrophies;
        //insert trophy
        assignmentActivity.tempTrophyArray(3);
        boolean foundthree = false, foundfour = false, foundten = false;

        //check if trophy 3 is inside the array
        for (int i = 0; i < assignmentActivity.myTrophies.length; i++) {
            if (assignmentActivity.myTrophies[i] == 3) {
                foundthree = true;
            }
            if (assignmentActivity.myTrophies[i] == 4) {
                foundfour = true;
            }
            if (assignmentActivity.myTrophies[i] == 10) {
                foundten = true;
            }
        }
        assertEquals(true, foundthree);
        assertEquals(true, foundfour);
        assertEquals(false, foundten);
    }

    @Test
    public void testFindTrophy() throws Exception {
        // has trophy 1,2,4,6
        // dont have trophy 3,5,7,8,9,10,11,12
        //will return true if the trophy is not found
        assertEquals(true, assignmentActivity.findTrophy(3));
        assertEquals(true, assignmentActivity.findTrophy(5));
        assertEquals(true, assignmentActivity.findTrophy(7));
        assertEquals(true, assignmentActivity.findTrophy(8));
        assertEquals(true, assignmentActivity.findTrophy(9));
        assertEquals(true, assignmentActivity.findTrophy(10));
        assertEquals(true, assignmentActivity.findTrophy(11));
        assertEquals(true, assignmentActivity.findTrophy(12));
        assertEquals(true, assignmentActivity.findTrophy(13));
        assertEquals(false, assignmentActivity.findTrophy(1));
        assertEquals(false, assignmentActivity.findTrophy(2));
        assertEquals(false, assignmentActivity.findTrophy(4));
        assertEquals(false, assignmentActivity.findTrophy(6));

    }


    @After
    public void tearDown() throws Exception {
        assignmentActivity = null;
    }


}