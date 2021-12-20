package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class TransportType(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<TransportData>,
    @SerializedName("message")
    val message: String
)