package uz.anorgroup.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.GET
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce

interface LocationApi {

    @GET("cities?name=tosh")
    suspend fun searchCity(): Response<SearchCityResponce>

    @GET("streets?city=1?name=")
    suspend fun searchStreet(): Response<SearchStreetsResponce>

}