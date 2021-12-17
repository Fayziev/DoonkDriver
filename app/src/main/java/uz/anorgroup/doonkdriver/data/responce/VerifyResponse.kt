package uz.anorgroup.doonkdriver.data.responce

data class VerifyResponse(
    val code: Int,
    val data: Data,
    val message: String
)

data class Data(
    val image: String,
    val code: String,
    val updatedAt: String,
    val gender: Int,
    val phone: String,
    val createdAt: String,
    val lastName: String,
    val id: Int,
    val deletedAt: Any,
    val firstName: String,
    val token: String
)
