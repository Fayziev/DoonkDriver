package uz.anorgroup.doonkdriver.data.request.car


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateCarRequest(

    @SerializedName("car_seet")
    var carSeet: List<CarSeet>? = null,
    @SerializedName("type_of_body")
    val typeOfBody: Int? = null,
    @SerializedName("type_of_transport")
    val typeOfTransport: Int? = null,
    @SerializedName("lifting_capacity")
    val liftingCapacity: Int? = null,
    @SerializedName("weight")
    val weight: Int? = null,
    @SerializedName("brand")
    val brand: Int? = null,
    @SerializedName("car_model")
    val carModel: Int? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("year_of_issue")
    val yearOfIssue: String? = null,
    @SerializedName("photos")
    val photos: List<Photo>? = null
):Parcelable