package uz.anorgroup.doonkdriver.data.request.car

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Addres(
    val city: Int,
    val street: Int
):Parcelable