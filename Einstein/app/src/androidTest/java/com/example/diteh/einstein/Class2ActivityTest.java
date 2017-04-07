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
public class Class2ActivityTest {

    @Rule
    public ActivityTestRule<Class2Activity> class2ActivityTestRule = new ActivityTestRule<Class2Activity>(Class2Activity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, Class2Activity.class);
            Bundle extras = new Bundle();
            extras.putString("name", "name");
            extras.putString("username", "username");
            result.putExtras(extras);
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorMainActivity = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    private Class2Activity class2Activity = null;

    @Before
    public void setUp() throws Exception {
        class2Activity = class2ActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfMainActivityOnBackToMainButtonClick() {
        assertNotNull(class2Activity.findViewById(R.id.backToMainButton));
        onView(withId(R.id.backToMainButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMainActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton1Click() {
        assertNotNull(class2Activity.findViewById(R.id.button1));
        onView(withId(R.id.button1)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton2Click() {
        assertNotNull(class2Activity.findViewById(R.id.button2));
        onView(withId(R.id.button2)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton3Click() {
        assertNotNull(class2Activity.findViewById(R.id.button3));
        onView(withId(R.id.button3)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton4Click() {
        assertNotNull(class2Activity.findViewById(R.id.button4));
        onView(withId(R.id.button4)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton5Click() {
        assertNotNull(class2Activity.findViewById(R.id.button5));
        onView(withId(R.id.button5)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton6Click() {
        assertNotNull(class2Activity.findViewById(R.id.button6));
        onView(withId(R.id.button6)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton7Click() {
        assertNotNull(class2Activity.findViewById(R.id.button7));
        onView(withId(R.id.button7)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfAssignmentActivityOnButton8Click() {
        assertNotNull(class2Activity.findViewById(R.id.button8));
        onView(withId(R.id.button8)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        class2Activity = null;
    }

}