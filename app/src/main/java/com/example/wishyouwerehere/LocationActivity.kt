package com.example.wishyouwerehere

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val location = intent.getParcelableExtra<Location>("LOCATION")

        // Within the Location Detail Activity "activity_location", find each view by id.
        val lImageView = findViewById<ImageView>(R.id.vImage)
        val lNameView = findViewById<TextView>(R.id.vLocation)
        val lAddressView = findViewById<TextView>(R.id.vAddress)
        val lRatingView = findViewById<RatingBar>(R.id.vRating)
        val lVisitedView = findViewById<TextView>(R.id.vDate)

        // Get The Image Resource using the location.fImage
        val imageRes = resources.getIdentifier(
            "l${location?.fImage}",
            "drawable",
            packageName
        )

        // For each view within the activity set the content to match that of the location
        lImageView.setImageResource(imageRes)
        lNameView.text = location?.fLocation
        lAddressView.text = location?.fAddress
        lVisitedView.text = location?.fLastVisited
        lRatingView.rating = location?.fRating!!
    }
}