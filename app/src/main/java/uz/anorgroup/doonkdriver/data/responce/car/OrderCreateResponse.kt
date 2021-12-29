package uz.anorgroup.doonkdriver.data.responce.car

import com.google.gson.annotations.SerializedName

data class OrderCreateResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class AddressItem(

	@field:SerializedName("city")
	val city: Int? = null,

	@field:SerializedName("street")
	val street: Int? = null
)

data class Data(

	@field:SerializedName("trailer")
	val trailer: Boolean? = null,

	@field:SerializedName("address")
	val address: List<AddressItem?>? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("car")
	val car: Int? = null,

	@field:SerializedName("luggage")
	val luggage: Boolean? = null,

	@field:SerializedName("count_of_client")
	val countOfClient: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("animal")
	val animal: Boolean? = null,

	@field:SerializedName("can_smoke")
	val canSmoke: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("date_of_departure")
	val dateOfDeparture: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null
)
