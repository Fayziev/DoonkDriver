package uz.anorgroup.doonkdriver.data.responce.auth


import com.google.gson.annotations.SerializedName

data class VerifyResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
)