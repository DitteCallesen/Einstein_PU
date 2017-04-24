package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Truong on 23.04.2017.
 */
public class TeachingActivityTest {
    @Rule
    public ActivityTestRule<TeachingActivity> TActivityTestRule = new ActivityTestRule<TeachingActivity>(TeachingActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, TeachingActivity.class);
            Bundle extras = new Bundle();
            extras.putString("username", "a");
            extras.putString("name", "admin");
            extras.putString("position", "admin");
            result.putExtras(extras);
            return result;
        }
    };
    Instrumentation.ActivityMonitor monitorClass1Activity = getInstrumentation().addMonitor(Class1Activity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorClass2Activity = getInstrumentation().addMonitor(Class2Activity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorListOfChatroomActivity = getInstrumentation().addMonitor(ListOfChatroomActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorCalenderActivity = getInstrumentation().addMonitor(CalendarActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorChartActivity = getInstrumentation().addMonitor(ChartActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorLoginActivity = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
    private TeachingActivity TActivity = null;


    @Before
    public void setUp() throws Exception {
        TActivity = TActivityTestRule.getActivity();
    }
    @Test
    public void onCreate() throws Exception {

        assertEquals("admin",TActivity.position);
        assertEquals("admin",TActivity.name);
        assertEquals("a",TActivity.username);
        TextView textView =(TextView) TActivity.findViewById(R.id.welcomeMessage);
        assertEquals("Welcome to Einstein, admin!",textView.getText().toString());

    }
    @Test
    public void testLaunchOfClass1ActivityOnButtonClick() {
        assertNotNull(TActivity.findViewById(R.id.class1Button));
        onView(withId(R.id.class1Button)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorClass1Activity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfClass2ActivityOnButtonClick() {
        assertNotNull(TActivity.findViewById(R.id.class2Button));
        onView(withId(R.id.class2Button)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorClass2Activity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfChatroomActivityOnButtonClick() {
        assertNotNull(TActivity.findViewById(R.id.chatroomButton));
        onView(withId(R.id.chatroomButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorListOfChatroomActivity, 7000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfCalenderActivityOnButtonClick() {
        assertNotNull(TActivity.findViewById(R.id.calendarButton));
        onView(withId(R.id.calendarButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorCalenderActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfChartActivityOnButtonClick() {
        assertNotNull(TActivity.findViewById(R.id.chartButton));
        onView(withId(R.id.chartButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorChartActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }


    @Test
    public void goToLogin() throws Exception {
        assertNotNull(TActivity.findViewById(R.id.logoutButton));
        onView(withId(R.id.logoutButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLoginActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void chartOnClick() throws Exception {

    }

}