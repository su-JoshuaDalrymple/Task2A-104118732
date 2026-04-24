package com.example.wishyouwerehere

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Location(
    val fLocation: String,      // Name Of Location
    val fAddress: String,       // Address Of Location
    val fRating: Float,         // Rating Of Location
    val fLastVisited: String,   // Date Last visited Location
    val fImage: String          // Image Reference to Location
) : Parcelable



