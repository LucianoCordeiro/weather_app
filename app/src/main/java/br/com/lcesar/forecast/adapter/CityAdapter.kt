package br.com.lcesar.forecast.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.lcesar.forecast.R
import br.com.lcesar.forecast.domain.City
import kotlinx.android.synthetic.main.adapter_city.view.*

class CityAdapter (val cities: List<City>): RecyclerView.Adapter<CityAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tMax: TextView
        var tMin: TextView
        init {
            tMax = view.findViewById(R.id.tMax)
            tMin = view.findViewById(R.id.tMin)
        }

    }

    override fun getItemCount(): Int = this.cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_city, parent, false)
        return CitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = cities[position]
        holder.tMin.text = city.temp_min
        holder.tMax.text = city.temp_max
    }

}