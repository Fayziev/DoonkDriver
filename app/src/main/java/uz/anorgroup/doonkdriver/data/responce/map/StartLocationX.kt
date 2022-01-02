package uz.anorgroup.doonkdriver.data.responce.map


import com.google.gson.annotations.SerializedName

data class StartLocationX(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)