package br.com.lcesar.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*

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
        val obj = JSONObject(json)
        val obj2 = obj.optString("coord")
        val obj3 = JSONObject(obj2)
        xalabaia.text = obj3.optString("lat")
        xalabaia2.text = obj.optString("weather")

    }
}
