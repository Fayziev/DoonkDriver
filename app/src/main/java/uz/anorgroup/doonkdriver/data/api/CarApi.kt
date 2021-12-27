package uz.anorgroup.doonkdriver.data.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.responce.car.*

interface CarApi {

    @POST("car")
    suspend fun carCreate(@Body data: CreateCarRequest2): Response<CreateCarResponce>

    @GET("brands")
    suspend fun brands(): Response<BrandsResponce>

    @GET("carModels")
    suspend fun carModels(): Response<ModelResponce>

    @GET("typesOfTransport")
    suspend fun typesOfAvto(): Response<TypeAvtoResponce>

    @GET("transportTypes")
    suspend fun transportTypes(): Response<TypeTransportsResponce>

    @GET("typesOfBody")
    suspend fun typesOfBody(): Response<TypeOfBodyResponce>

    @GET("cars")
    suspend fun getCarsInfo(): Response<AllCarsResponse>

    @Multipart
    @POST("image")
    suspend fun uploadImage(@Part file: MultipartBody.Part): Response<ImageUploadResponse>

}