package uz.anorgroup.doonkdriver.data.request.car


import com.google.gson.annotations.SerializedName

data class CreateCarRequest(
    @SerializedName("brand")
    val brand: Int,
    @SerializedName("car_model")
    val carModel: Int,
    @SerializedName("car_seet")
    val carSeet: List<CarSeet>,
    @SerializedName("color")
    val color: String,
    @SerializedName("lifting_capacity")
    val liftingCapacity: Int,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("type_of_body")
    val typeOfBody: Int,
    @SerializedName("type_of_transport")
    val typeOfTransport: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("year_of_issue")
    val yearOfIssue: String
)