package uz.anorgroup.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uz.anorgroup.doonkdriver.data.responce.map.MapResponse

interface MapApi {

    @GET
    suspend fun getDirection(
        @Url url: String = "https://maps.googleapis.com/maps/api/directions/json",
        @Query("origin", encoded = true) origin: String,
        @Query("destination", encoded = true) destination: String,
        @Query("key", encoded = true) key: String
    ): Response<MapResponse>

}