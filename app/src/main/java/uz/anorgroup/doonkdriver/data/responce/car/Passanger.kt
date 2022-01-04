package uz.anorgroup.doonkdriver.data.responce.car

import java.io.Serializable

data class Passanger(
    val address: Any,
    val animal: Boolean,
    val can_smoke: Boolean,
    val car: Int,
    val count_of_client: Int,
    val date_of_departure: String,
    val id: Int,
    val luggage: Boolean,
    val status: Int,
    val trailer: Boolean
):Serializable