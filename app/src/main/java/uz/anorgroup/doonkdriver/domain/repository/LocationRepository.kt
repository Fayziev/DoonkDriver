package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce

interface LocationRepository {
    fun searchCity(name:String): Flow<Result<SearchCityResponce>>
    fun searchStreet(city:String,name:String): Flow<Result <SearchStreetsResponce>>
}