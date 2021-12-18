package uz.anorgroup.doonkdriver.data.responce.location


import com.google.gson.annotations.SerializedName

data class SearchCityResponce(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<DataCity>,
    @SerializedName("message")
    val message: String
)