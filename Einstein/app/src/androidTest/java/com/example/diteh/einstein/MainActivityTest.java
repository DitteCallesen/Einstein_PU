package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by mariasoleim on 24.03.2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, MainActivity.class);
            Bundle extras = new Bundle();
            extras.putString("jsonO", "{\"userdata\":[{\"username\":\"a\",\"taskID\":\"10\",\"courseSubjectID\":\"1\",\"ansInARow\":\"7\",\"Asolved\":\"0\",\"correctOnFirstTry\":\"10\"}],\"assignments\":[{\"task\":\"x + 4 = 6_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"1\",\"assignID\":\"1\"},{\"task\":\"4 + x = 8_x = 4_x = 5_x = 6_x = 7\",\"difficulty\":\"1\",\"assignID\":\"3\"},{\"task\":\"-8 = 2 - x_x = 10_x = 12_x = 14_x = 16\",\"difficulty\":\"1\",\"assignID\":\"4\"},{\"task\":\"8 - x = 7_x = 1_x = 2_x=3_x=4\",\"difficulty\":\"1\",\"assignID\":\"5\"},{\"task\":\"5 + x = 21_x = 16_x = 21_x = 1_x = 5_x = 3\",\"difficulty\":\"1\",\"assignID\":\"6\"},{\"task\":\"x - 3 = 4_x = 7_x = 4_x = 6_x = 11\",\"difficulty\":\"1\",\"assignID\":\"7\"},{\"task\":\"8 = x - 5_x = 13_x = 5_x = 10_x = 12_x = 8\",\"difficulty\":\"1\",\"assignID\":\"8\"},{\"task\":\"4x + 3x = 14_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"2\",\"assignID\":\"9\"},{\"task\":\"12 + x = 5x_x = 3_ x = 4_ x = 5_ x = 6\",\"difficulty\":\"2\",\"assignID\":\"10\"},{\"task\":\"8x + 18 = 26x_x = 1_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"11\"},{\"task\":\"2x*4 = 2x + 12_x = 2_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"12\"},{\"task\":\"4 - 2x = 2*2x + 1_x = 2_x = 1_x = -1_x = 3\",\"difficulty\":\"2\",\"assignID\":\"13\"},{\"task\":\"x + 15 = 36_x = 21_x = 13_x = 14_x = 15\",\"difficulty\":\"1\",\"assignID\":\"14\"}]");
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
    Instrumentation.ActivityMonitor monitorTrophyroomActivity = getInstrumentation().addMonitor(TrophyActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorLoginActivity = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
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
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorListOfChatroomActivity, 7000);
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
    public void goToLogin() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.logoutButton));
        onView(withId(R.id.logoutButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLoginActivity, 5000);
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

    @Test
    public void testBackGroundClass() {
        mActivity.username = "Test1";
        //onView(withId(R.id.trophyroomButton)).perform(click());
        mActivity.trophyOnClick(null);
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTrophyroomActivity, 5000);
        //user Test1 has only two trophies, 1 and 10, check if activiated
        assertEquals(nextActivity.findViewById(R.id.trophy1).getVisibility(), View.VISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy10).getVisibility(), View.VISIBLE);
        //check no other trophies are activated
        assertEquals(nextActivity.findViewById(R.id.trophy2).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy3).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy4).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy5).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy6).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy7).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy8).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy9).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy11).getVisibility(), View.INVISIBLE);
        assertEquals(nextActivity.findViewById(R.id.trophy12).getVisibility(), View.INVISIBLE);
    }

    @Test
    public void testclass1OnClick() {

        mActivity.class1OnClick(null);
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorClass1Activity, 5000);
        assertNotNull(nextActivity);

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}