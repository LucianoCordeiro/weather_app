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
    var icon = ""

    companion object {
        fun getCities(): List<City> {
            val listCities = mutableListOf<City>()
            val urlArray: Array<String> =
                    arrayOf("http://api.openweathermap.org/data/2.5/weather?q=Curitiba,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=Florianopolis,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=São Paulo,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                            "http://api.openweathermap.org/data/2.5/weather?q=Porto Alegre,br,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                            "http://api.openweathermap.org/data/2.5/weather?q=Belo Horizonte,br&appid=5c076a102a0cec6b20ac1c722cafc77b"
                    )
            for (url in urlArray) {
                val json = URL(url).readText()
                val city = parserJson(json)
                listCities.add(city)
            }
            return listCities
        }

        private fun parserJson(json: String): City {
            val name = JSONObject(json).optString("name")
            val city = City()
            city.name = name
            city.temp = temp(json)
            city.main = main(json)
            city.icon = "http://openweathermap.org/img/w/${icon(json)}.png"
            return city
        }

        private fun temp(json: String): String {
            val obj = JSONObject(json).optString("main")
            return JSONObject(obj).optString("temp")
        }

        private fun main(json: String): String {
            val obj = JSONObject(json)
            val array = obj.getJSONArray("weather")
            return array.getJSONObject(0).optString("main")
        }

        private fun icon(json: String): String {
            val obj = JSONObject(json)
            val array = obj.getJSONArray("weather")
            return array.getJSONObject(0).optString("icon")
        }


    }


}