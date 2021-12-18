package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class BrandsResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<CarData>,
    @SerializedName("message")
    val message: String
)