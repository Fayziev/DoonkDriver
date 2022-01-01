package uz.anorgroup.doonkdriver.data.responce.car

import com.google.gson.annotations.SerializedName
import uz.anorgroup.doonkdriver.data.request.car.AddressItem

data class OrderCreateResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

//data class AddressItem(
//
//	@field:SerializedName("city")
//	val city: Int,
//
//	@field:SerializedName("street")
//	val street: Int
//)

data class Data(

	@field:SerializedName("trailer")
	val trailer: Boolean,

	@field:SerializedName("address")
	val address: List<AddressItem?>,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("car")
	val car: Int,

	@field:SerializedName("luggage")
	val luggage: Boolean,

	@field:SerializedName("count_of_client")
	val countOfClient: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("animal")
	val animal: Boolean,

	@field:SerializedName("can_smoke")
	val canSmoke: Boolean,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("date_of_departure")
	val dateOfDeparture: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any
)
