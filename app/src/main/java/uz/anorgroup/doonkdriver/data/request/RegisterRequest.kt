package uz.anorgroup.doonkdriver.data.request

data class RegisterRequest(
    val code: String,
    val phone: String,
    val last_name: String,
    val first_name: String
)

