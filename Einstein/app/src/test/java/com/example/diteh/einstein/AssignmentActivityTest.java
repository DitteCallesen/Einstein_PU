package com.example.diteh.einstein;

import org.json.JSONArray;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

/**
 * Created by SimonBeast on 20.03.2017.
 */
public class AssignmentActivityTest {

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void getMessage() throws Exception {
        AssignmentActivity assignmentActivity = new AssignmentActivity();
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
        AssignmentActivity assignmentActivity = new AssignmentActivity();
        JSONArray inputArray;
        int inputTaskId;
        boolean output;
        boolean expected;

        String[] data = {"stringone", "stringtwo"};
        inputArray = new JSONArray(Arrays.asList(data));
        inputTaskId = 0;
        output = assignmentActivity.nextTaskExists(inputArray, inputTaskId);
        expected = true;
        assertEquals(expected, output);
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