package uz.anorgroup.doonkdriver.data.request.auth

data class VerifyRequest(
    val code: String,
    val phone: String
)
