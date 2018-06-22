package br.com.lcesar.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.lcesar.forecast.domain.City
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var city: City
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        city = intent.getSerializableExtra("city") as City

        cName.text = "${city.name}, Brazil"
        temp.text = "${(city.temp.toDouble() - 273.15).toInt()}°C"
        main.text = "|   ${city.main}"
        description.text = "Condition: ${city.description}"
        humidity.text = "Humidity: ${city.humidity}%"
        pressure.text = "Air Pressure: ${city.pressure} hPa"
        speed.text = "Wind Speed: ${(city.speed.toDouble() * 3.6).toInt()} km/h"
        sunrise.text = "Sunrise: ${city.sunrise}"
        sunset.text = "Sunset: ${city.sunset}"
        Picasso.get().load(city.icon).into(img)
    }
}
