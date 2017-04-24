package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Truong on 24.04.2017.
 */
public class ChartActivityTest {
    @Rule
    public ActivityTestRule<ChartActivity> CActivityTestRule = new ActivityTestRule<ChartActivity>(ChartActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, ChartActivity.class);
            Bundle extras = new Bundle();
            extras.putString("username", "a");
            extras.putString("name", "admin");
            extras.putString("position", "admin");
            extras.putString("courseSubject", "{\"server_response\":{\"course\":[{\"courseID\":\"1\",\"course\":\"Mathematics\"},{\"courseID\":\"2\",\"course\":\"Statistics\"}],\"subject\":[{\"subjectID\":\"1\",\"subject\":\"Algebra\"},{\"subjectID\":\"2\",\"subject\":\"Percent\"},{\"subjectID\":\"3\",\"subject\":\"Geometry\"},{\"subjectID\":\"5\",\"subject\":\"Sample mean\"},{\"subjectID\":\"6\",\"subject\":\"Sample median\"},{\"subjectID\":\"7\",\"subject\":\"Variance\"},{\"subjectID\":\"8\",\"subject\":\"Standard Deviation\"},{\"subjectID\":\"9\",\"subject\":\"Additive Rules\"},{\"subjectID\":\"10\",\"subject\":\"Product Rule\"},{\"subjectID\":\"11\",\"subject\":\"Bayes Rule\"},{\"subjectID\":\"12\",\"subject\":\"Number systems\"},{\"subjectID\":\"13\",\"subject\":\"Multiplication\"},{\"subjectID\":\"14\",\"subject\":\"Division\"},{\"subjectID\":\"15\",\"subject\":\"Area\"},{\"subjectID\":\"16\",\"subject\":\"Pythagoras\"}]}}");
            result.putExtras(extras);
            return result;
        }
    };
    Instrumentation.ActivityMonitor monitorTeachingActivity = getInstrumentation().addMonitor(TeachingActivity.class.getName(), null, false);
    private ChartActivity CActivity = null;


    @Before
    public void setUp() throws Exception {
        CActivity = CActivityTestRule.getActivity();
    }
    @Test
    public void onCreate() throws Exception {
        assertNotNull(CActivity.findViewById(R.id.backButton));
        assertNotNull(CActivity.findViewById(R.id.getdata));
    }

    @Test
    public void btBack() throws Exception {
        onView(withId(R.id.backButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity,5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void onBackPressed() throws Exception {
        CActivity.onBackPressed();
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity,5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void setTable() throws Exception {

    }

}