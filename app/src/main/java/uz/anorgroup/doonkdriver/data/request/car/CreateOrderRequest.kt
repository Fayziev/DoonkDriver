package uz.anorgroup.doonkdriver.data.request.car

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateOrderRequest(
    val parcel: Parcel,
    val passanger: Passanger
):Parcelable