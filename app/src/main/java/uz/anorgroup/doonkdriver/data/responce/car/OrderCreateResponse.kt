package uz.anorgroup.doonkdriver.data.responce.car

import com.google.gson.annotations.SerializedName

data class OrderCreateResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("data")
	val data: Data? = null,

	@SerializedName("message")
	val message: String? = null
)

data class ItemAddress(

	@SerializedName("city")
	val city: Int? = null,

	@SerializedName("street")
	val street: Int? = null
)

data class Data(

	@SerializedName("trailer")
	val trailer: Boolean? = null,

	@SerializedName("address")
	val address: List<ItemAddress>? = null,

	@SerializedName("updated_at")
	val updatedAt: String? = null,

	@SerializedName("car")
	val car: Int? = null,

	@SerializedName("luggage")
	val luggage: Boolean? = null,

	@SerializedName("count_of_client")
	val countOfClient: Int? = null,

	@SerializedName("created_at")
	val createdAt: String? = null,

	@SerializedName("animal")
	val animal: Boolean? = null,

	@SerializedName("can_smoke")
	val canSmoke: Boolean? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("date_of_departure")
	val dateOfDeparture: String? = null,

	@SerializedName("deleted_at")
	val deletedAt: Any? = null
)
