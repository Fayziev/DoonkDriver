package uz.anorgroup.doonkdriver.data.responce.auth

data class LoginResponse(
    val code: Int,
    val data: Any,
    val message: String
)
