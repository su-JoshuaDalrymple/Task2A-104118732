package com.example.wishyouwerehere

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import java.util.Calendar
import android.widget.EditText
import androidx.activity.addCallback
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity()
{
    private var locationIndex: Int = -1
    private lateinit var locationData: Location

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        locationIndex = intent.getIntExtra("LOCATION_INDEX", -1)
        if (locationIndex == -1) return

        val lData = intent.getParcelableExtra<Location>("LOCATION")
        if( lData != null )
        {
            locationData = lData.copy()
        }
        else
        {
            return
        }

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


        lVisitedView.addTextChangedListener(object : android.text.TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!isValidDate())
                {
                    lVisitedView.error = "Enter a full date (DD/MM/YYYY)"
                }
                else
                {
                    lVisitedView.error = null
                }
            }
        })

        onBackPressedDispatcher.addCallback(this)
        {
            if (locationIndex != -1) {
                if( isUpdated() )
                {
                    locationData.fLocation = lNameView.text.toString()
                    locationData.fAddress = lAddressView.text.toString()
                    locationData.fRating = lRatingView.rating
                    if(isValidDate()) {
                        locationData.fLastVisited = lVisitedView.text.toString()
                    }
                }
            }
            val resultIntent = Intent().apply {
                putExtra("LOCATION", locationData)
                putExtra("LOCATION_INDEX", locationIndex)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
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

    private fun isValidDate(): Boolean {
        val currentDate = findViewById<EditText>(R.id.vDate).text.toString()
        val regex = """^(\d{1,2})/(\d{1,2})/(\d{4})$""".toRegex()
        if(currentDate.matches(regex))
        {
            val (day, month, year) = currentDate.split("/")
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            return (
                    day.toInt() in 1..31 &&
                    month.toInt() in 1..12 &&
                    year.toInt() in 1900 ..currentYear
            )
        }
        return false
    }
}