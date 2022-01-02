package uz.anorgroup.doonkdriver.data.responce.map


import com.google.gson.annotations.SerializedName

data class OverviewPolyline(
    @SerializedName("points")
    val points: String
)