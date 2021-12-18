package uz.anorgroup.doonkdriver.data.request.car


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("photo")
    val photo: String
)