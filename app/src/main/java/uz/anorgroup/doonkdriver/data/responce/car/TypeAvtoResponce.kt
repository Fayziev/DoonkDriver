package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class TypeAvtoResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<AvtoData>,
    @SerializedName("message")
    val message: String
)