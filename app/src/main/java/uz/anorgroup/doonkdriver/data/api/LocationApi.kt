package uz.anorgroup.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce

interface LocationApi {
    @GET("cities?")
    suspend fun searchCity(@Query("name") name: String): Response<SearchCityResponce>

    @GET("streets?")
    suspend fun searchStreet(@Query("city") city: String, @Query("name") name: String): Response<SearchStreetsResponce>

}