package uz.anorgroup.doonkdriver.data.responce.map


import com.google.gson.annotations.SerializedName

data class Distance(
    @SerializedName("text")
    val text: String,
    @SerializedName("value")
    val value: Int
)