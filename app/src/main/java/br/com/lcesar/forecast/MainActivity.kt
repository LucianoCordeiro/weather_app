package br.com.lcesar.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.lcesar.forecast.adapter.CityAdapter
import br.com.lcesar.forecast.domain.City
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private var cities = listOf<City>()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        taskCities()
    }

    fun taskCities() {
        doAsync {
            cities = City.getCities()
            uiThread {
                recyclerView.adapter = CityAdapter(cities)
            }
        }

    }

}
