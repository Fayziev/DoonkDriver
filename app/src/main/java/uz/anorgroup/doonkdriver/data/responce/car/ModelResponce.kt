package uz.anorgroup.doonkdriver.data.responce.car


import com.google.gson.annotations.SerializedName

data class ModelResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<ModelData>,
    @SerializedName("message")
    val message: String
)