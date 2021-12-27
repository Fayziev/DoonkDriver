package uz.anorgroup.doonkdriver.data.others

data class ChatData constructor(
    val date: String,
    val time: String,
    val name: String,
    val placeName: String,
    val senderPlace: String,
    val chatText: String
)

