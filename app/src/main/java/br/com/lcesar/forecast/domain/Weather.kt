package br.com.lcesar.forecast.domain

import br.com.lcesar.forecast.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class City {
    var id:Long = 0
    var name = ""
    var main = ""
    var description = ""
    var temp = ""
    var temp_min = ""
    var temp_max = ""
    var humidity = ""

    companion object {
        fun getCities(): List<City> {
            val listCities = mutableListOf<City>()
            val urlArray: Array<String> =
                    arrayOf("http://api.openweathermap.org/data/2.5/weather?q=Curitiba,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=Florianopolis,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=São Paulo,br&appid=5c076a102a0cec6b20ac1c722cafc77b"
                    )
            for (url in urlArray) {
                val json = URL(url).readText()
                val city = parserJson(json)
                listCities.add(city)
            }
            return listCities
        }

        private fun parserJson(json: String): City {
            val obj = JSONObject(json).optString("main")
            val tempMax = JSONObject(obj).optString("temp_max")
            val tempMin = JSONObject(obj).optString("temp_min")
            val city = City()
            city.temp_max = tempMax
            city.temp_min = tempMin
            return city
        }


    }


}