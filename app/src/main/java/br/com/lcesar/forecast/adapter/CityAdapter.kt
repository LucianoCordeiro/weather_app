package br.com.lcesar.forecast.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lcesar.forecast.R
import br.com.lcesar.forecast.domain.City
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter (val cities: List<City>, val onClick: (City) -> Unit): RecyclerView.Adapter<CityAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int = this.cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false)
        return CitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = cities[position]
        val view = holder.itemView
        view.cName.text = "${city.name}, Brazil"
        view.temp.text = "${(city.temp.toDouble() - 273.15).toInt()}°C"
        view.main.text = "|   ${city.main}"
        Picasso.get().load(city.icon).into(view.img)
        view.button.setTextColor(Color.parseColor("#ffffff"))
        view.button.background.setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.MULTIPLY)
        view.button.setOnClickListener { onClick(city) }
    }

}