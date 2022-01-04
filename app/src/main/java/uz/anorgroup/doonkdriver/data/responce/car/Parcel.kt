package uz.anorgroup.doonkdriver.data.responce.car

import java.io.Serializable

data class Parcel(
    val address: Any,
    val comment: String,
    val height: Int,
    val id: Int,
    val length: Int,
    val price: Int,
    val status: Int,
    val type: Int,
    val weight: Int,
    val width: Int
):Serializable