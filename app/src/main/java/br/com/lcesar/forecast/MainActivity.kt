package br.com.lcesar.forecast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.lcesar.forecast.adapter.CityAdapter
import br.com.lcesar.forecast.domain.City
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


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
                recyclerView.adapter = CityAdapter(cities) {onClickCity(it)}
            }
        }
    }

    private fun onClickCity(city: City) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("city", city)
        startActivity(intent)
    }
}
