package br.com.lcesar.forecast.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.lcesar.forecast.R
import br.com.lcesar.forecast.domain.City
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter (val cities: List<City>): RecyclerView.Adapter<CityAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cName: TextView
        var temp: TextView
        var img: ImageView
        var main: TextView
        init {
            temp = view.findViewById(R.id.temp)
            cName = view.findViewById(R.id.cName)
            img = view.findViewById(R.id.img)
            main = view.findViewById(R.id.main)
        }

    }

    override fun getItemCount(): Int = this.cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false)
        return CitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = cities[position]
        holder.cName.text = "${city.name}, Brazil"
        holder.temp.text = "${(city.temp.toDouble() - 273.15).toInt()}°C"
        holder.main.text = "|   ${city.main}"
        Picasso.get().load(city.icon).into(holder.img)
    }

}