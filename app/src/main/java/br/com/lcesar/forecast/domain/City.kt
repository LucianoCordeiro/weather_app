package br.com.lcesar.forecast.domain

import org.json.JSONObject
import java.io.Serializable
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class City : Serializable {
    var id:Long = 0
    var name = ""
    var main = ""
    var description = ""
    var temp = ""
    var pressure = ""
    var humidity = ""
    var speed = ""
    var sunrise = ""
    var sunset = ""
    var lat = ""
    var lon = ""
    var icon = ""

    companion object {
        fun getCities(): List<City> {
            val listCities = mutableListOf<City>()
            val urlArray: Array<String> =
                    arrayOf("http://api.openweathermap.org/data/2.5/weather?q=Curitiba,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=Florianopolis,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                        "http://api.openweathermap.org/data/2.5/weather?q=São Paulo,br&appid=5c076a102a0cec6b20ac1c722cafc77b",
                            "http://api.openweathermap.org/data/2.5/weather?q=Porto Alegre,br,br&appid=5c076a102a0cec6b20ac1c722cafc77b"
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
            with(city) {
                temp = getData(json, "main", "temp")
                humidity = getData(json, "main", "humidity")
                pressure = getData(json, "main", "pressure")
                speed = getData(json, "wind", "speed")
                description = getWeatherData(json, "description")
                main = getWeatherData(json, "main")
                sunrise = dateParser(json, "sunrise")
                sunset = dateParser(json, "sunset")
                lat = getData(json, "coord", "lat")
                lon = getData(json, "coord", "lon")
                icon = "http://openweathermap.org/img/w/${getWeatherData(json, "icon")}.png"
            }
            return city
        }

        private fun getData(json: String, firstKeyword: String, lastKeyword: String): String {
            val obj = JSONObject(json).optString(firstKeyword)
            return JSONObject(obj).optString(lastKeyword)
        }

        private fun getWeatherData(json: String, keyword: String): String {
            val obj = JSONObject(json)
            val array = obj.getJSONArray("weather")
            return array.getJSONObject(0).optString(keyword)
        }

        private fun dateParser(json: String, lastKeyword: String): String {
            val time = getData(json, "sys", lastKeyword).toLong()
            val date = Date((time * 1000) - 10800000)
            return SimpleDateFormat("HH:mm").format(date)
        }

    }
}