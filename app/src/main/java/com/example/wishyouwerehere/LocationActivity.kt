package com.example.wishyouwerehere

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {
    private var locationIndex: Int = -1
    private lateinit var locationData: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        locationIndex = intent.getIntExtra("LOCATION_INDEX", -1)
        if (locationIndex == -1) return

        locationData = LocationList.fLocations[locationIndex].copy()

        val lImageView = findViewById<ImageView>(R.id.vImage)
        val lNameView = findViewById<EditText>(R.id.vLocation)
        val lAddressView = findViewById<EditText>(R.id.vAddress)
        val lRatingView = findViewById<RatingBar>(R.id.vRating)
        val lVisitedView = findViewById<EditText>(R.id.vDate)

        val imageRes = resources.getIdentifier(
            "l${locationData.fImage}",
            "drawable",
            packageName
        )

        lImageView.setImageResource(imageRes)
        lNameView.setText(locationData.fLocation)
        lAddressView.setText(locationData.fAddress)
        lRatingView.rating = locationData.fRating
        lVisitedView.setText(locationData.fLastVisited)
    }

    override fun onPause() {
        super.onPause()

        if (locationIndex != -1) {
            if( isUpdated() )
            {
                val newName = findViewById<EditText>(R.id.vLocation).text.toString()
                val newAddress = findViewById<EditText>(R.id.vAddress).text.toString()
                val newDate = findViewById<EditText>(R.id.vDate).text.toString()
                val newRating = findViewById<RatingBar>(R.id.vRating).rating

                LocationList.fLocations[locationIndex].fLocation = newName
                LocationList.fLocations[locationIndex].fAddress = newAddress
                LocationList.fLocations[locationIndex].fLastVisited = newDate
                LocationList.fLocations[locationIndex].fRating = newRating
                LocationList.lastUpdatedIndex = locationIndex
            }
        }
    }

    private fun isUpdated(): Boolean {
        val currentName = findViewById<EditText>(R.id.vLocation).text.toString()
        val currentAddress = findViewById<EditText>(R.id.vAddress).text.toString()
        val currentDate = findViewById<EditText>(R.id.vDate).text.toString()
        val currentRating = findViewById<RatingBar>(R.id.vRating).rating

        return (
            currentName != locationData.fLocation ||
            currentAddress != locationData.fAddress ||
            currentDate != locationData.fLastVisited ||
            currentRating != locationData.fRating
        )
    }
}