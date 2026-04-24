// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // ADDED FOR PARCELIZE
    alias(libs.plugins.kotlin.parcelize) apply false
}