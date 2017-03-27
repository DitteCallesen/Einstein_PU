package com.example.diteh.einstein;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mariasoleim on 27.03.2017.
 */
public class TrophyActivityTest {

    @Rule
    public ActivityTestRule<TrophyActivity> trophyActivityTestRule = new ActivityTestRule<TrophyActivity>(TrophyActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, TrophyActivity.class);
            Bundle extras = new Bundle();
            extras.putString("", "");
            result.putExtras(extras);
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    private TrophyActivity TrophyActivity = null;

    @Before
    public void setUp() throws Exception {
        TrophyActivity = trophyActivityTestRule.getActivity();
    }

    @Test
    public void testViewOfBigTrophyOnTrophyClick() {
        assertNotNull(TrophyActivity.findViewById(R.id.trophy1));
        onView(withId(R.id.trophy1)).perform(click());
        assertEquals(TrophyActivity.findViewById(R.id.big_trophy).getVisibility(), View.VISIBLE);
    }

    @After
    public void tearDown() throws Exception {
        TrophyActivity = null;
    }

}