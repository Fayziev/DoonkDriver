package uz.anorgroup.doonkdriver.data.responce.car

data class Data(
    val address: List<Addres>,
    val animal: Boolean,
    val can_smoke: Boolean,
    val car: Int,
    val count_of_client: Int,
    val created_at: String,
    val date_of_departure: String,
    val deleted_at: Any,
    val id: Int,
    val luggage: Boolean,
    val trailer: Boolean,
    val updated_at: String
)