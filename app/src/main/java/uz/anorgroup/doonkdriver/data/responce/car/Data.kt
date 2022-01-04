package uz.anorgroup.doonkdriver.data.responce.car

data class Data(
    val id: Int,
    val created_at: String,
    val updated_at: String,
    val deleted_at: Any,
    val car: Int,
    val address: List<Addres>,
    val date_of_departure: String,
    val count_of_client: Int,
    val luggage: Boolean,
    val animal: Boolean,
    val can_smoke: Boolean,
    val trailer: Boolean
)