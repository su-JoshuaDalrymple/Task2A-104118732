package com.example.wishyouwerehere

object LocationList {
    val fLocations = listOf(
        Location(
            fLocation = "Lake Wendouree",
            fAddress = "Wendouree Parade, Ballarat VIC 3350",
            fRating = 3.0f,
            fLastVisited = "14/05/2025",
            fImage = "82376"
        ),
        Location(
            fLocation = "Sovereign Hill",
            fAddress = "Bradshaw St, Golden Point VIC 3350",
            fRating = 4.0f,
            fLastVisited = "22/08/2025",
            fImage = "93876"
        ),
        Location(
            fLocation = "Ballarat Botanical Gardens",
            fAddress = "Gillie St, Lake Wendouree VIC 3350",
            fRating = 5.0f,
            fLastVisited = "03/11/2054",
            fImage = "45678"
        ),
        Location(
            fLocation = "Art Gallery of Ballarat",
            fAddress = "40 Lydiard St N, Ballarat Central VIC 3350",
            fRating = 2.0f,
            fLastVisited = "21/02/2026",
            fImage = "56892"
        )
    )

    val size: Int = fLocations.size
}

