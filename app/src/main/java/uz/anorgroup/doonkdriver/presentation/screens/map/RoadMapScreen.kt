package uz.anorgroup.doonkdriver.presentation.screens.map

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ActivityMapsBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.map.MapViewModelImpl


@AndroidEntryPoint
class RoadMapScreen : Fragment(R.layout.screen_road_map), OnMapReadyCallback {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val viewmodel by viewModels<MapViewModelImpl>()
    private val listPoints = ArrayList<LatLng>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_screen) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setUpMap()
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMapLongClickListener {
            if (listPoints.size == 2) {
                listPoints.clear()
                map.clear()
            }
            listPoints.add(it)
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            if (listPoints.size == 1) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            } else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            map.addMarker(markerOptions)
            if (listPoints.size == 2) {
                viewmodel.getMap("41.334505,69.274003", "41.279401,69.217261", "AIzaSyBZiaoR6LhBci4bSDYJynj54hVLYZgm6pA")
            }
        }
        viewmodel.successFlow.onEach { latLangs ->
            val polylineOptions = PolylineOptions()
            polylineOptions.addAll(latLangs)
            polylineOptions.width(15f)
            polylineOptions.color(Color.BLUE)
            polylineOptions.geodesic(true)
            map.addPolyline(polylineOptions)
        }.launchIn(lifecycleScope)
    }

    private fun setUpMap() {
        val granted = PackageManager.PERMISSION_GRANTED
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != granted &&
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != granted
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                0
            )
            map.isMyLocationEnabled = true
            return
        }

        task.addOnSuccessListener {
            val yourCoordinate = LatLng(it.latitude, it.longitude)
            val destanation = LatLng(41.26465, 69.21627)
            map.addMarker(MarkerOptions().position(yourCoordinate).title("You"))
            map.addMarker(MarkerOptions().position(destanation).title("Amir Temur"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(destanation, 15f))

//                val line = map.addPolyline(
//                    PolylineOptions()
//                        .add(LatLng(it.latitude, it.longitude), LatLng(41.26465, 69.21627))
//                        .width(5f)
//                        .color(Color.RED)
//                )
        }

    }
}