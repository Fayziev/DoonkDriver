package uz.anorgroup.doonkdriver.data

data class OrdersInfo constructor(
    var companyName: String,
    var timeOut: String,
    val desciption: String,
    val timeCome: String,
    val timeTrip: String,
    val place: String,
    val cost: String,
    val placeCount: String
)

