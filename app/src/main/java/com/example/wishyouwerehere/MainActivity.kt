package com.example.wishyouwerehere

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: LocationListAdapter
    private val locationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val lUpdatedLocation = data?.getParcelableExtra<Location>("LOCATION")

            val lIndex = data?.getIntExtra("LOCATION_INDEX", -1) ?: -1

            if (lUpdatedLocation != null && lIndex != -1) {
                LocationList.fLocations[lIndex] = lUpdatedLocation
                adapter.notifyItemChanged(lIndex)
                val mainLayout = findViewById<View>(R.id.MainActivityLayout)
                mainLayout?.let {
                    Snackbar.make(it, "${lUpdatedLocation.fLocation} Updated!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locations = findViewById<RecyclerView>(R.id.locationList)

        adapter = LocationListAdapter { lPosition ->
            val lIntent = Intent(this, LocationActivity::class.java).apply {
                putExtra("LOCATION_INDEX", lPosition)
                putExtra("LOCATION", LocationList.fLocations[lPosition])
            }
            locationLauncher.launch(lIntent)
        }
        locations.adapter = adapter
        locations.layoutManager = GridLayoutManager(this, 2)
    }
}
