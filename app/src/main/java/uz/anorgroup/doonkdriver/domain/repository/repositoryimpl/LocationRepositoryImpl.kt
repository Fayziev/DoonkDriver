package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.anorgroup.doonkdriver.data.api.LocationApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce
import uz.anorgroup.doonkdriver.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val api: LocationApi, private val pref: MyPref) : LocationRepository {

    override fun searchCity(): Flow<Response<SearchCityResponce>> {
        TODO("Not yet implemented")
    }

    override fun searchStreet(): Flow<Response<SearchStreetsResponce>> {
        TODO("Not yet implemented")
    }
}