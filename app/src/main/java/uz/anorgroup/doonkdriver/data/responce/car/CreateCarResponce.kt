package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class CreateCarResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String
)