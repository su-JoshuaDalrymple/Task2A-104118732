package com.example.wishyouwerehere

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationListAdapter : RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    // From parent context create a view using "obj_location"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.obj_location, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = LocationList.size

    // When the view is bound to the parent, find the location from the list using the "position" argument as the index
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lLocation = LocationList.fLocations[position]
        holder.bind(lLocation)
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        // Declare each view within the "obj_location" layout.
        val fNameView = v.findViewById<TextView>(R.id.vName)
        val fRatingView = v.findViewById<RatingBar>(R.id.vRating)
        val fImageView = v.findViewById<ImageView>(R.id.vImage)

        // When the view is bound, set the view within the layout to the data within the location object
        fun bind(item: Location) {
            val imageRes = v.context.resources.getIdentifier(
                "l${item.fImage}",
                "drawable",
                v.context.packageName
            )
            fNameView.text = item.fLocation
            fRatingView.rating = item.fRating
            fImageView.setImageResource(imageRes)

            // Set the onClickLister, that it creates an Intent, and passthrough the parcelable "Location" object into detailed location activity
            v.setOnClickListener {
                val intent = Intent(v.context, LocationActivity::class.java)
                intent.putExtra("LOCATION", item)
                v.context.startActivity(intent)
            }
        }
    }
}
