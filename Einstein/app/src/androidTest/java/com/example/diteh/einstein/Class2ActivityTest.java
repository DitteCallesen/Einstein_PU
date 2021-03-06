package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mariasoleim on 26.03.2017.
 */
public class Class2ActivityTest {
    //initiate Class2Activity for test
    @Rule
    public ActivityTestRule<Class2Activity> class2ActivityTestRule = new ActivityTestRule<Class2Activity>(Class2Activity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, Class2Activity.class);
            Bundle extras = new Bundle();
            extras.putString("name", "admin");
            extras.putString("username", "a");
            extras.putString("position", "admin");
            result.putExtras(extras);
            return result;
        }
    };
    //initiate activit monitors
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AssignmentActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorMainActivity = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorTeachingActivity = getInstrumentation().addMonitor(TeachingActivity.class.getName(), null, false);
    private Class2Activity class2Activity = null;

    @Before
    public void setUp() throws Exception {
        class2Activity = class2ActivityTestRule.getActivity();
    }

    //test method backToMain
    @Test
    public void testBackToMainForTeacherMethods() {
        //go to back to main menu, first to teachingsite
        class2Activity.backToMain(null);
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity, 5000);
        assertNotNull(nextActivity);

        //test to go to main for students
        class2Activity.position = "Student";
        class2Activity.backToMain(null);
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMainActivity, 5000);
        assertNotNull(nextActivity);


        //test onClick method
        View view = class2Activity.findViewById(R.id.button1);
        class2Activity.onClick(view);
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
    }
    //test method onBackPressed
    @Test
    public void testOnBackPressed() {

        //go to back to main menu, first to teachingsite
        class2Activity.onBackPressed();

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity, 5000);
        assertNotNull(nextActivity);

        //test to go to main for students
        class2Activity.position = "Student";
        class2Activity.onBackPressed();
        nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMainActivity, 5000);
        assertNotNull(nextActivity);
    }

    //rest of the tests here are to test button clicks for the different subjects
    @Test
    public void testLaunchOfClass1ActivityOnBackToTeachingButtonClick() {
        assertNotNull(class2Activity.findViewById(R.id.backToMainButton));
        onView(withId(R.id.backToMainButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfClass1ActivityOnBackToMainButtonClick() {
        class2Activity.position = "Student";
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
        onView(withId(R.id.button7)).perform(scrollTo(), click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }


    @After
    public void tearDown() throws Exception {
        class2Activity = null;
    }

}