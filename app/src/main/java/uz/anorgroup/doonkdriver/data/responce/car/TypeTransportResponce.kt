package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class TypeTransportResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<TransportTypeData>,
    @SerializedName("message")
    val message: String
)