package uz.anorgroup.doonkdriver.data.request.car


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @SerializedName("photo")
    val photo: String
):Parcelable

