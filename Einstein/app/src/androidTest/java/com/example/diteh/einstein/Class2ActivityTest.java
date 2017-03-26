package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by mariasoleim on 26.03.2017.
 */
public class Class2ActivityTest {

    @Rule
    public ActivityTestRule<Class2Activity> class2ActivityTestRule = new ActivityTestRule<Class2Activity>(Class2Activity.class) {
        @Override
        protected Intent getActivityIntent() {
            String js_string = "";
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, MainActivity.class);
            Bundle extras = new Bundle();
            extras.putString("CLASS_ID", "Mathematics");
            extras.putString("SUBJECT_ID", "subject_id");
            extras.putInt("TASK_ID", 1);
            extras.putInt("CORRECT_ANSWERS_IN_A_ROW", 1);
            extras.putInt("CORRECT_ON_FIRST_TRY", 1);
            extras.putInt("NUMBER_OF_TASKS", 1);
            try {
                JSONObject jsonObject = new JSONObject(js_string);
                extras.putString("jsonO", jsonObject.getJSONObject("server_response").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            extras.putString("name", "name");
            extras.putString("username", "username");
            result.putExtras(extras);
            return result;
        }
    };
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    private Class2Activity class2Activity = null;

    @Before
    public void setUp() throws Exception {
        class2Activity = class2ActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton1Click() {
        assertNotNull(class2Activity.findViewById(R.id.button1));
        onView(withId(R.id.button1)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        class2Activity = null;
    }

}