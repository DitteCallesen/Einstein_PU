package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

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
public class LoginActivityTest {
    //initiate loginactivity for test
    @Rule
    public ActivityTestRule<LoginActivity> LActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, LoginActivity.class);
            return result;
        }
    };
    //initiate activity monitors
    Instrumentation.ActivityMonitor monitorMainActivity = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorTeachingActivity = getInstrumentation().addMonitor(TeachingActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorRegisterActivity = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);
    private LoginActivity LActivity = null;


    @Before
    public void setUp() throws Exception {
        LActivity = LActivityTestRule.getActivity();
    }
    @Test
    public void onCreate() throws Exception {
        assertNotNull(LActivity.findViewById(R.id.blogin));
        assertNotNull(LActivity.findViewById(R.id.tvRegisterhere));
    }

    //test button click register
    @Test
    public void testRegisterButtonClick(){
        onView(withId(R.id.tvRegisterhere)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorRegisterActivity,5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    //test if you can login with exsisting non student user (admin or teacher)
    @Test
    public void testLoginButtonClickForAdmin(){
        final EditText etUsername = (EditText) LActivity.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) LActivity.findViewById(R.id.etPassword);

        //user is an admin
        LActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etUsername.setText("a");
                etPassword.setText("a");

            }
        });
        onView(withId(R.id.blogin)).perform(click());

        //check if right information has been sent from database
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorTeachingActivity,5000);
        assertNotNull(nextActivity);
        Bundle extras = nextActivity.getIntent().getExtras();
        String position, name, username;
        position = extras.getString("position");
        name = extras.getString("name");
        username=extras.getString("username");
        assertEquals(position, "admin");
        assertEquals(name, "admin");
        assertEquals(username,"a");
        nextActivity.finish();
    }

    //test if you can login with exsisting student
    @Test
    public void testLoginButtonClickForStudents(){
        final EditText etUsername = (EditText) LActivity.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) LActivity.findViewById(R.id.etPassword);

        //user is an admin
        LActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etUsername.setText("Test1");
                etPassword.setText("a");

            }
        });
        onView(withId(R.id.blogin)).perform(click());
        //check if right information has been sent from database
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorMainActivity,5000);
        assertNotNull(nextActivity);
        Bundle extras = nextActivity.getIntent().getExtras();
        String position, name, username;
        position = extras.getString("position");
        name = extras.getString("name");
        username=extras.getString("username");
        assertEquals(position, "Student");
        assertEquals(name, "ForBrukerTest1");
        assertEquals(username,"Test1");
        nextActivity.finish();
    }


}