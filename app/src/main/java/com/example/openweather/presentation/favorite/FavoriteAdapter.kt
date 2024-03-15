package com.example.openweather.presentation.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.R
import com.example.openweather.utils.Constants.KEY_CITY_NAME
import com.example.openweather.presentation.weather_details.DetailsActivity

class FavoriteAdapter(private val cityList: List<String>) :
    RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_favorite_list, parent, false)
        return FavoriteViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.cityName.text = cityList[position]
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cityName: AppCompatTextView = itemView.findViewById(R.id.tvCityName)

    init {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, DetailsActivity::class.java)
            intent.putExtra(KEY_CITY_NAME, cityName.text)
            itemView.context.startActivity(intent)
        }
    }

}
