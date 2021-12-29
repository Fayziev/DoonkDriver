package uz.anorgroup.doonkdriver.data.request.car

import com.google.gson.annotations.SerializedName

data class OrderCreateRequest(

	@SerializedName("trailer")
	val trailer: Boolean? = null,

	@SerializedName("address")
	val address: List<AddressItem?>? = null,

	@SerializedName("car")
	val car: Int? = null,

	@SerializedName("luggage")
	val luggage: Boolean? = null,

	@SerializedName("count_of_client")
	val countOfClient: Int? = null,

	@SerializedName("animal")
	val animal: Boolean? = null,

	@SerializedName("can_smoke")
	val canSmoke: Boolean? = null,

	@SerializedName("date_of_departure")
	val dateOfDeparture: String? = null
)

data class AddressItem(

	@SerializedName("city")
	val city: Int? = null,

	@SerializedName("street")
	val street: Int? = null
)
