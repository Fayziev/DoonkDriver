package uz.anorgroup.doonkdriver.data.responce.auth


data class RegisterResponse(
    val code: Int,
    val data: Data,
    val message: String
)