package uz.anorgroup.doonkdriver.data.responce.location


import com.google.gson.annotations.SerializedName

data class SearchStreetsResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<DataStreet>,
    @SerializedName("message")
    val message: String
)