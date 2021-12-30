package uz.anorgroup.doonkdriver.data.request.car

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderCreateRequest(

    @SerializedName("car")
    val car: Int? = null,

    @SerializedName("address")
    val address: List<AddressItem>? = null,

    @SerializedName("date_of_departure")
    val dateOfDeparture: String? = null,

    @SerializedName("count_of_client")
    val countOfClient: Int? = null,

    @SerializedName("trailer")
    val trailer: Boolean? = null,


    @SerializedName("luggage")
    val luggage: Boolean? = null,

    @SerializedName("animal")
    val animal: Boolean? = null,

    @SerializedName("can_smoke")
    val canSmoke: Boolean? = null
) : Parcelable

@Parcelize
data class AddressItem(

    @SerializedName("city")
    val city: Int? = null,

    @SerializedName("street")
    val street: Int? = null
) : Parcelable
