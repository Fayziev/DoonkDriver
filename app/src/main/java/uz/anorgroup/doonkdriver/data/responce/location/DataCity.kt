package uz.anorgroup.doonkdriver.data.responce.location


import com.google.gson.annotations.SerializedName

data class DataCity(
    @SerializedName("country")
    val country: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)