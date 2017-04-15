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
 * Created by mariasoleim on 24.03.2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, MainActivity.class);
            result.putExtra("", "");
            return result;
        }
    };
    Instrumentation.ActivityMonitor monitorClass1Activity = getInstrumentation().addMonitor(Class1Activity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorClass2Activity = getInstrumentation().addMonitor(Class2Activity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorChatroomActivity = getInstrumentation().addMonitor(ChatroomActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorCalenderActivity = getInstrumentation().addMonitor(CalendarActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorTrophyroomActivity = getInstrumentation().addMonitor(TrophyActivity.class.getName(), null, false);
    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfClass1ActivityOnButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.class1Button));
        onView(withId(R.id.class1Button)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorClass1Activity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfClass2ActivityOnButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.class2Button));
        onView(withId(R.id.class2Button)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorClass2Activity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfChatroomActivityOnButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.chatroomButton));
        onView(withId(R.id.chatroomButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorChatroomActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfCalenderActivityOnButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.calendarButton));
        onView(withId(R.id.calendarButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorCalenderActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testLaunchOfTrophyActivityOnButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.trophyroomButton));
        onView(withId(R.id.trophyroomButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTrophyroomActivity, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}