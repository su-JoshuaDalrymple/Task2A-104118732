package com.example.wishyouwerehere

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize

data class Location(
    var fLocation: String,      // Name Of Location
    var fAddress: String,       // Address Of Location
    var fRating: Float,         // Rating Of Location
    var fLastVisited: String,   // Date Last visited Location
    var fImage: String          // Image Reference to Location
) : Parcelable



