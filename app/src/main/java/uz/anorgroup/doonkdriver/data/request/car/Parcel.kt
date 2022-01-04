package uz.anorgroup.doonkdriver.data.request.car

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parcel(
    val address: List<AddresX>?=null,
    val height: Int?=null,
    val length: Int?=null,
    val type: Int?=null,
    val weight: Int?=null,
    val width: Int?=null
):Parcelable