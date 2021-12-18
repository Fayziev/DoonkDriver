package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class TypeOfBodyResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<TypeBodyData>,
    @SerializedName("message")
    val message: String
)