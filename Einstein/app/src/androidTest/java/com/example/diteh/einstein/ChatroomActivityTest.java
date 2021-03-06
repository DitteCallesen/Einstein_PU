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
 * Created by mariasoleim on 27.03.2017.
 */
public class ChatroomActivityTest {
    //Initiating Chart activity for test
    @Rule
    public ActivityTestRule<Chatroom> chatroomActivityTestRule = new ActivityTestRule<Chatroom>(Chatroom.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, Chatroom.class);
            Bundle extras = new Bundle();
            extras.putString("username", "a");
            extras.putString("name", "admin");
            extras.putString("position", "admin");
            extras.putString("roomName", "Room1");
            result.putExtras(extras);
            return result;
        }
    };

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ListOfChatroomActivity.class.getName(), null, false);
    private Chatroom ChatroomActivity = null;

    //test if teaching activity is launched when back button is pressed
    @Before
    public void setUp() throws Exception {
        ChatroomActivity = chatroomActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfMainActivityOnBackToMainButtonClick() {
        assertNotNull(ChatroomActivity.findViewById(R.id.backToListOfRooms));
        onView(withId(R.id.backToListOfRooms)).perform(click());
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        ChatroomActivity = null;
    }

}