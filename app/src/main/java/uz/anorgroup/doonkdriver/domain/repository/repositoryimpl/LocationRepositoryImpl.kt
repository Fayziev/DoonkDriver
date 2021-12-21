package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.LocationApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce
import uz.anorgroup.doonkdriver.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val api: LocationApi, private val pref: MyPref) : LocationRepository {

    override fun searchCity(name: String): Flow<Result<SearchCityResponce>> = flow {
        val response = api.searchCity(name)
        if (response.isSuccessful) {
            emit(Result.success<SearchCityResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {

        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))

    }.flowOn(Dispatchers.IO)

    override fun searchStreet(city: String, name: String): Flow<Result<SearchStreetsResponce>> = flow {
        val responce = api.searchStreet(city, name)
        if (responce.isSuccessful) {
            emit(Result.success<SearchStreetsResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)
}