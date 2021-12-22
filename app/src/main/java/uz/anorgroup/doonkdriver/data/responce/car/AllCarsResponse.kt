package uz.anorgroup.doonkdriver.data.responce.car

import com.google.gson.annotations.SerializedName

data class AllCarsResponse(

	@SerializedName("code")
	val code: Int,
	@SerializedName("data")
	val data: List<DataItem?>,
	@SerializedName("message")
	val message: String
)

data class DataItem(
	@SerializedName("model")
	val model: String,
	@SerializedName("id")
	val id: Int,
	@SerializedName("brand")
	val brand: String
)
