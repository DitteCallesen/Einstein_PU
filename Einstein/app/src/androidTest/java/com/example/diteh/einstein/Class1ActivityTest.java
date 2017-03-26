package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by mariasoleim on 26.03.2017.
 */
public class Class1ActivityTest {

    @Rule
    public ActivityTestRule<Class1Activity> class1ActivityTestRule = new ActivityTestRule<Class1Activity>(Class1Activity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, Class1Activity.class);
            Bundle extras = new Bundle();
            extras.putString("name", "name");
            extras.putString("username", "username");
            result.putExtras(extras);
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorMainActivity = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    private Class1Activity class1Activity = null;

    @Before
    public void setUp() throws Exception {
        class1Activity = class1ActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfMainActivityOnBackToMainButtonClick() {
        assertNotNull(class1Activity.findViewById(R.id.backToMainButton));
        onView(withId(R.id.backToMainButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMainActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton1Click() {
        assertNotNull(class1Activity.findViewById(R.id.button1));
        onView(withId(R.id.button1)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton2Click() {
        assertNotNull(class1Activity.findViewById(R.id.button2));
        onView(withId(R.id.button2)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton3Click() {
        assertNotNull(class1Activity.findViewById(R.id.button3));
        onView(withId(R.id.button3)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton4Click() {
        assertNotNull(class1Activity.findViewById(R.id.button4));
        onView(withId(R.id.button4)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton5Click() {
        assertNotNull(class1Activity.findViewById(R.id.button5));
        onView(withId(R.id.button5)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton6Click() {
        assertNotNull(class1Activity.findViewById(R.id.button6));
        onView(withId(R.id.button6)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton7Click() {
        assertNotNull(class1Activity.findViewById(R.id.button7));
        onView(withId(R.id.button7)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton8Click() {
        assertNotNull(class1Activity.findViewById(R.id.button8));
        onView(withId(R.id.button8)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        class1Activity = null;
    }

}