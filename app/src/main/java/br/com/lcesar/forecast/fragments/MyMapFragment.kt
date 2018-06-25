package br.com.lcesar.forecast.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lcesar.forecast.CityActivity
import br.com.lcesar.forecast.R
import br.com.lcesar.forecast.domain.City
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MyMapFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        val city = arguments?.getSerializable("city") as City
        val location = LatLng(city.lat.toDouble(), city.lon.toDouble())
        val update = CameraUpdateFactory.newLatLngZoom(location, 13f)
        map.moveCamera(update)
        map.addMarker(MarkerOptions()
                .title(city.name)
                .snippet(city.description)
                .position(location))
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
    }
}