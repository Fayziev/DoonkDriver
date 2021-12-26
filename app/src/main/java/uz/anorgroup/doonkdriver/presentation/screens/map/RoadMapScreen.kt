package uz.anorgroup.doonkdriver.presentation.screens.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ActivityMapsBinding

@AndroidEntryPoint
class RoadMapScreen : Fragment(R.layout.screen_road_map), OnMapReadyCallback {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var polyline: Polyline


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_screen) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setUpMap()
        map.uiSettings.isZoomControlsEnabled = true
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
            if (it != null) {
                val yourCoordinate = LatLng(it.latitude, it.longitude)
                val destanation = LatLng(41.26465, 69.21627)
                map.addMarker(MarkerOptions().position(yourCoordinate).title("You"))
                map.addMarker(MarkerOptions().position(destanation).title("Amir Temur"))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(yourCoordinate, 15f))

            }

        }

    }
}