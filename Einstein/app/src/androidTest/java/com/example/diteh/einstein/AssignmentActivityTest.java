package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
            result.putExtra("jsonO", "{'assignment': [{'task':'x+4=6_x=1_x=3_x=9_x=4'},{'task':'x+4=6_x=1_x=3_x=9_x=4'}]}");
            result.putExtra("Name", "Value");
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    private AssignmentActivity assignmentActivity = null;

    @Before
    public void setUp() throws Exception {
        assignmentActivity = assignmentActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfMainActivityOnButtonClick() {
        assertNotNull(assignmentActivity.findViewById(R.id.backToMainButton));
        onView(withId(R.id.backToMainButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        assignmentActivity = null;
    }


}