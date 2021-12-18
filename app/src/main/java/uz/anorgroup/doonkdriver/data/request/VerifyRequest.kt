package uz.anorgroup.doonkdriver.data.request

data class VerifyRequest(
    val code: String,
    val phone: String
)
