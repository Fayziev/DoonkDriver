package uz.anorgroup.doonkdriver.data.responce.car

import com.google.gson.annotations.SerializedName

data class CarsData(

	@SerializedName("model")
	val model: String,
	@SerializedName("id")
	val id: Int,
	@SerializedName("brand")
	val brand: String
)
