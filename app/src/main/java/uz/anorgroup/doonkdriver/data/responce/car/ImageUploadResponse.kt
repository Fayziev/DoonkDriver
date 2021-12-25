package uz.anorgroup.doonkdriver.data.responce.car

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ImageUploadResponse(

	@SerializedName("code")
	val code: Int,

	@SerializedName("data")
	val data: Photo,

	@SerializedName("message")
	val message: String
)
@Parcelize
data class Photo(

	@SerializedName("path")
	val path: String
):Parcelable
