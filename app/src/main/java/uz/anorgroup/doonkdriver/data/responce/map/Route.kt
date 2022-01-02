package uz.anorgroup.doonkdriver.data.responce.map


import com.google.gson.annotations.SerializedName
import uz.anorgroup.doonkdriver.data.responce.map.Bounds
import uz.anorgroup.doonkdriver.data.responce.map.Leg
import uz.anorgroup.doonkdriver.data.responce.map.OverviewPolyline

data class Route(
    @SerializedName("bounds")
    val bounds: Bounds,
    @SerializedName("copyrights")
    val copyrights: String,
    @SerializedName("legs")
    val legs: List<Leg>,
    @SerializedName("overview_polyline")
    val overviewPolyline: OverviewPolyline,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("warnings")
    val warnings: List<Any>,
    @SerializedName("waypoint_order")
    val waypointOrder: List<Any>
)