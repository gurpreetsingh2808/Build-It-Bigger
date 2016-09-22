package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Gurpreet on 9/22/2016.
 */

@RunWith(AndroidJUnit4.class)
public class JokesExpressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void asyncTaskTest() {

        //  click on joker card
        onView(withId(R.id.rlCard))
                .perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        //MainActivity mainActivity = new MainActivity();
        //assertNotNull(EndpointsAsyncTask().execute(mainActivity).get());
        //  swipe cards are visible only if data is not null
        onView(withId(R.id.swipeStack)).check(matches(isDisplayed()));
    }
}
