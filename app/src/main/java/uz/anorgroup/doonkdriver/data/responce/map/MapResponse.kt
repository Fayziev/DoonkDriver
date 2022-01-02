package uz.anorgroup.doonkdriver.data.responce.map


import com.google.gson.annotations.SerializedName

data class MapResponse(
    @SerializedName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypoint>,
    @SerializedName("routes")
    val routes: List<Route>,
    @SerializedName("status")
    val status: String
)