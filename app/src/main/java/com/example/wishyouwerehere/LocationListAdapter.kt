package com.example.wishyouwerehere

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationListAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.obj_location, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = LocationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lLocation = LocationList.fLocations[position]
        holder.bind(lLocation, position, onItemClick)
    }

    class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val fNameView: TextView = v.findViewById(R.id.vName)
        val fRatingView: RatingBar = v.findViewById(R.id.vRating)
        val fImageView: ImageView = v.findViewById(R.id.vImage)

        fun bind(item: Location, position: Int, onClick: (Int) -> Unit) {
            val imageRes = v.context.resources.getIdentifier(
                "l${item.fImage}",
                "drawable",
                v.context.packageName
            )
            fNameView.text = item.fLocation
            fRatingView.rating = item.fRating
            fImageView.setImageResource(imageRes)

            v.setOnClickListener {
                onClick(position)
            }
        }
    }
}
