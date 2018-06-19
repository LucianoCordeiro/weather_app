package br.com.lcesar.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            val str = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=5c076a102a0cec6b20ac1c722cafc77b"
            val json = URL(str).readText()
            runOnUiThread {
                parserJson(json)
            }
        }.start()
    }

    private fun parserJson(json: String) {
        coordinatesParser(json)
        weatherParser(json)

    }

    private fun weatherParser(json: String) {
        val obj = JSONObject(json).optString("weather")
        val array = JSONArray(obj)
        val jsonObject = array.getJSONObject(0)
        xalabaia3.text = jsonObject.optString("description")
    }

    private fun coordinatesParser(json: String) {
        val obj = JSONObject(json).optString("coord")
        val latitude = JSONObject(obj).optString("lat")
        val longitude = JSONObject(obj).optString("lon")

        xalabaia.text = latitude
        xalabaia2.text = longitude
    }


}
