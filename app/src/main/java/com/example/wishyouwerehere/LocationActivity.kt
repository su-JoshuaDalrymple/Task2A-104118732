package com.example.wishyouwerehere

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {
    private var locationIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        locationIndex = intent.getIntExtra("LOCATION_INDEX", -1)
        if (locationIndex == -1) return
        val location = LocationList.fLocations[locationIndex]

        val lImageView = findViewById<ImageView>(R.id.vImage)
        val lNameView = findViewById<EditText>(R.id.vLocation)
        val lAddressView = findViewById<EditText>(R.id.vAddress)
        val lRatingView = findViewById<RatingBar>(R.id.vRating)
        val lVisitedView = findViewById<EditText>(R.id.vDate)

        val imageRes = resources.getIdentifier(
            "l${location.fImage}",
            "drawable",
            packageName
        )

        lImageView.setImageResource(imageRes)
        lNameView.setText(location.fLocation)
        lAddressView.setText(location.fAddress)
        lRatingView.rating = location.fRating
        lVisitedView.setText(location.fLastVisited)
    }

    override fun onPause() {
        super.onPause()


        if (locationIndex != -1) {
            val name = findViewById<EditText>(R.id.vLocation).text.toString()
            val address = findViewById<EditText>(R.id.vAddress).text.toString()
            val date = findViewById<EditText>(R.id.vDate).text.toString()

            LocationList.fLocations[locationIndex].fLocation = name
            LocationList.fLocations[locationIndex].fAddress = address
            LocationList.fLocations[locationIndex].fLastVisited = date
        }
    }
}