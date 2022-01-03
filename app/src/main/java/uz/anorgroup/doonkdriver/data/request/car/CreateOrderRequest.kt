package uz.anorgroup.doonkdriver.data.request.car

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateOrderRequest(
    val car: Int?=null,
    val address: List<Addres>?=null,
    val date_of_departure: String?=null,
    val count_of_client: Int?=null,
    val animal: Boolean?=null,
    val can_smoke: Boolean?=null,
    val luggage: Boolean?=null,
    val trailer: Boolean?=null
):Parcelable