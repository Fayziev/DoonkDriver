package uz.gita.doonkdriver.data.request

data class VerifyRequest(
    val code: String,
    val phone: String
)
