package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
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
            extras.putString("jsonO", "{\"userdata\":[{\"username\":\"a\",\"taskID\":\"10\",\"courseSubjectID\":\"1\",\"ansInARow\":\"7\",\"Asolved\":\"0\",\"correctOnFirstTry\":\"10\"}],\"assignments\":[{\"task\":\"x + 4 = 6_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"1\",\"assignID\":\"1\"},{\"task\":\"4 + x = 8_x = 4_x = 5_x = 6_x = 7\",\"difficulty\":\"1\",\"assignID\":\"3\"},{\"task\":\"-8 = 2 - x_x = 10_x = 12_x = 14_x = 16\",\"difficulty\":\"1\",\"assignID\":\"4\"},{\"task\":\"8 - x = 7_x = 1_x = 2_x=3_x=4\",\"difficulty\":\"1\",\"assignID\":\"5\"},{\"task\":\"5 + x = 21_x = 16_x = 21_x = 1_x = 5_x = 3\",\"difficulty\":\"1\",\"assignID\":\"6\"},{\"task\":\"x - 3 = 4_x = 7_x = 4_x = 6_x = 11\",\"difficulty\":\"1\",\"assignID\":\"7\"},{\"task\":\"8 = x - 5_x = 13_x = 5_x = 10_x = 12_x = 8\",\"difficulty\":\"1\",\"assignID\":\"8\"},{\"task\":\"4x + 3x = 14_x = 2_x = 3_x = 4_x = 5\",\"difficulty\":\"2\",\"assignID\":\"9\"},{\"task\":\"12 + x = 5x_x = 3_ x = 4_ x = 5_ x = 6\",\"difficulty\":\"2\",\"assignID\":\"10\"},{\"task\":\"8x + 18 = 26x_x = 1_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"11\"},{\"task\":\"2x*4 = 2x + 12_x = 2_x = 7_x = 8_x = 9\",\"difficulty\":\"2\",\"assignID\":\"12\"},{\"task\":\"4 - 2x = 2*2x + 1_x = 2_x = 1_x = -1_x = 3\",\"difficulty\":\"2\",\"assignID\":\"13\"},{\"task\":\"x + 15 = 36_x = 21_x = 13_x = 14_x = 15\",\"difficulty\":\"1\",\"assignID\":\"14\"}]");
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
    public void testShowTrophies() {
        ImageButton imageButton;

        //all trophies are invisible here
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy1);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy2);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy3);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy4);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy5);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy6);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy7);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy8);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy9);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy10);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy11);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy12);
        assertEquals(imageButton.getVisibility(), View.INVISIBLE);
        TrophyActivity.runOnUiThread(new Runnable() {

            public void run() {
                ImageButton imageButton;
                int[] trophies = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

                //this makes all trophies visiable
                for (int i = 0; i < trophies.length; i++) {
                    TrophyActivity.showTrophies(trophies[i]);
                }

                //check if the abot is true
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy1);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy2);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy3);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy4);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy5);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy6);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy7);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy8);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy9);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy10);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy11);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
                imageButton = (ImageButton) TrophyActivity.findViewById(R.id.trophy12);
                assertEquals(imageButton.getVisibility(), View.VISIBLE);
            }
        });


    }


    @Test
    public void testViewOfBigTrophyOnTrophyClick() {

        TrophyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TrophyActivity.showTrophies(1);
            }
        });
        assertNotNull(TrophyActivity.findViewById(R.id.trophy1));
        onView(withId(R.id.trophy1)).perform(click());
        assertEquals(TrophyActivity.findViewById(R.id.big_trophy).getVisibility(), View.VISIBLE);
    }

    @Test
    public void testTrophyClicked() {
        TextView textView;
        TrophyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 13; i++) {
                    TrophyActivity.showTrophies(i);
                }
            }
        });

        onView(withId(R.id.trophy1)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved your first exercise.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy2)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Answered correctly on 5 exercises in a row.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy3)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Answered correctly on 10 exercises in a row.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy4)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Worked 5 days in one week.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy5)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Worked 5 days every week for 3 weeks.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy6)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Worked 20 days in a month.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy7)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 5 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy8)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 10 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy9)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 50 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy10)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 100 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy11)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 500 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());

        onView(withId(R.id.trophy12)).perform(click());
        textView = (TextView) TrophyActivity.findViewById(R.id.trophy_text);
        assertEquals(textView.getText(), "Solved 1000 exercises.");
        onView(withId(R.id.big_trophy)).perform(click());
    }

    @Test
    public void testLaunchOfMainActivityOnBackButtonClick() {
        assertNotNull(TrophyActivity.findViewById(R.id.backButton));
        onView(withId(R.id.backButton)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testOnBackPressed() {
        TrophyActivity.onBackPressed();
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {

    }

}