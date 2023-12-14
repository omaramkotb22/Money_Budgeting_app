package com.example.mdp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void ensureEmailFieldWorks() {
        // Type text into the email field and then check if the text is displayed
        Espresso.onView(withId(R.id.emailEditText))
                .perform(typeText("test@example.com"), ViewActions.closeSoftKeyboard())
                .check(ViewAssertions.matches(withText("test@example.com")));
    }

    @Test
    public void ensurePasswordFieldWorks() {
        // Type text into the password field and then check if the text is displayed
        Espresso.onView(withId(R.id.passwordEditText))
                .perform(typeText("123456"), ViewActions.closeSoftKeyboard())
                .check(ViewAssertions.matches(withText("123456")));
    }

    @Test
    public void attemptLoginWithEmptyFields() {
        // Click on the login button
        Espresso.onView(withId(R.id.loginButton)).perform(click());

        // Check if the error is displayed on the email field
        Espresso.onView(withId(R.id.emailEditText))
                .check(ViewAssertions.matches(ViewMatchers.hasErrorText("Email is required")));
    }

    // Other tests can be added to check different scenarios like invalid email, password too short, etc.
}
