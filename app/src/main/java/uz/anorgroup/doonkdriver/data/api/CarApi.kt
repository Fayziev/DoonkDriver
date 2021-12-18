package uz.anorgroup.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.*

interface CarApi {

    @POST("car")
    suspend fun carCreate(@Body data: CreateCarRequest): Response<CreateCarResponce>

    @GET("brands")
    suspend fun brands(): Response<BrandsResponce>

    @GET("carModels")
    suspend fun carModels(): Response<ModelResponce>

    @GET("typesOfTransport")
    suspend fun typesOfTransport(): Response<TypeTransportResponce>

    @GET("typesOfBody")
    suspend fun typesOfBody(): Response<TypeOfBodyResponce>

}