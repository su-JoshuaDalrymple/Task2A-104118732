package com.example.wishyouwerehere

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.wishyouwerehere", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class LocationAppTesting {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testOpenAllDetailedLocationActivities() {

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("Lake Wendouree")))
        pressBack()

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("Sovereign Hill")))
        pressBack()

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("Ballarat Botanical Gardens")))
        pressBack()

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("Art Gallery of Ballarat")))
        pressBack()

    }

    @Test
    fun testDateValidation() {
        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.vDate)).check(matches(withText("14/05/2025")))
        onView(withId(R.id.vDate)).perform(replaceText("1/1/1"), closeSoftKeyboard())
        pressBack()

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vDate)).check(matches(withText("14/05/2025")))
        onView(withId(R.id.vDate)).perform(replaceText("10/10/2010"), closeSoftKeyboard())
        pressBack()

        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vDate)).check(matches(withText("10/10/2010")))

    }

    @Test
    fun testEditLocationName() {
        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("Lake Wendouree")))
        onView(withId(R.id.vLocation)).perform(replaceText("NewLocationName"), closeSoftKeyboard())
        pressBack()

        onView(withText("NewLocationName")).check(matches(isDisplayed()))
        onView(withId(R.id.locationList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vLocation)).check(matches(withText("NewLocationName")))
    }
}