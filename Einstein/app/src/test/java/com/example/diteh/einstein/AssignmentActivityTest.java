package com.example.diteh.einstein;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SimonBeast on 20.03.2017.
 */
public class AssignmentActivityTest {

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void getMessage() throws Exception {
        int test1input1 = 5;
        int test1input2 = 10;

        int test2input1 = 5;
        int test2input2 = 0;

        int test3input1 = 6;
        int test3input2 = 10;

        String output1;
        String output2;
        String output3;

        String expected1 = "Better luck next time.";
        String expected2 = "No exercises available.";
        String expected3 = "Congratulations!";

        AssignmentActivity assignmentActivity = new AssignmentActivity();

        output1 = assignmentActivity.getMessage(test1input1,test1input2);
        output2 = assignmentActivity.getMessage(test2input1,test2input2);
        output3 = assignmentActivity.getMessage(test3input1,test3input2);

        //assertEquals(expected1, output1);
        //assertEquals(expected2, output2);
        //assertEquals(expected3, output3);
    }

    @Test
    public void nextTaskExists() throws Exception {

    }

    @Test
    public void nextTask() throws Exception {

    }

    @Test
    public void goToNextTask() throws Exception {

    }

    @Test
    public void correctAnswerClicked() throws Exception {

    }

    @Test
    public void wrongAnswerClicked() throws Exception {

    }

    @Test
    public void button1Clicked() throws Exception {

    }

    @Test
    public void button2Clicked() throws Exception {

    }

    @Test
    public void button3Clicked() throws Exception {

    }

    @Test
    public void button4Clicked() throws Exception {

    }

    @Test
    public void backToMain() throws Exception {

    }

    @Test
    public void randomizer() throws Exception {

    }

    @Test
    public void addTrophy() throws Exception {

    }

    @Test
    public void findTrophy() throws Exception {

    }

}