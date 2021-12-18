package uz.anorgroup.doonkdriver.data.request.car


import com.google.gson.annotations.SerializedName

data class CarSeet(
    @SerializedName("position")
    val position: Int,
    @SerializedName("qty")
    val qty: Int
)