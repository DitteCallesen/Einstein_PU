package com.example.diteh.einstein;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
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
public class RegisterActivityTest {
    //initiate registerActvity for test
    @Rule
    public ActivityTestRule<RegisterActivity> RActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, RegisterActivity.class);
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitorLoginActivity = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
    private RegisterActivity RActivity = null;


    @Before
    public void setUp() throws Exception {
        RActivity = RActivityTestRule.getActivity();
    }
    @Test
    public void onCreate() throws Exception {
        assertNotNull(RActivity.findViewById(R.id.bRegister));
        assertNotNull(RActivity.findViewById(R.id.bBack));
    }

    /**Check if you can use existing username and email to create new account.
    New account can only be made once, if you want to test it, change username and email
    then change success,nameAvail and emailAvil to true, and also all the assert should be
    changed to true before the test is launched **/
    @Test
    public void testRegisterButtonClick(){
        final EditText etName = (EditText) RActivity.findViewById(R.id.etName);
        final EditText etUsername = (EditText) RActivity.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) RActivity.findViewById(R.id.etPass1);
        final EditText etPassword2 = (EditText) RActivity.findViewById(R.id.etPass2);
        final EditText etEmail = (EditText) RActivity.findViewById(R.id.etEmail);
        JSONObject jsonObject;

        //this user exist
        RActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etEmail.setText("Something@online.com");
                etName.setText("Nils");
                etUsername.setText("Super1");
                etPassword.setText("a");
                etPassword2.setText("a");
            }
        });
        onView(withId(R.id.bRegister)).perform(click());
        SystemClock.sleep(5000);
        jsonObject = RActivity.jsonResponse;
        boolean success=false,nameAvail=false,emailAvil=false;
        try {
            success = jsonObject.getBoolean("success");
            nameAvail = jsonObject.getBoolean("nameAvil");
            emailAvil = jsonObject.getBoolean("emailAvil");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(false,success);
        assertEquals(false,nameAvail);
        assertEquals(false,emailAvil);
    }

    //test method checkValid
    @Test
    public void checkValid() throws Exception {
        RActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(false,RActivity.checkValid("a","A","something@online.com"));
                assertEquals(true,RActivity.checkValid("a","a","something@online.com"));
                assertEquals(false,RActivity.checkValid("a","a","somethingonline.com"));
            }
        });

    }

    @Test
    public void backOnClick() throws Exception {
        onView(withId(R.id.bBack)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLoginActivity,5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
    @Test
    public void onPressedBack() throws Exception {
        RActivity.onBackPressed();
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLoginActivity,5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

}