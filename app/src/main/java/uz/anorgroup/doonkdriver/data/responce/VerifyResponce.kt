package uz.anorgroup.doonkdriver.data.responce


import com.google.gson.annotations.SerializedName

data class VerifyResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
)