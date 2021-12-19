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

    override fun searchCity(): Flow<Result<SearchCityResponce>> = flow {
        val responce = api.searchCity()
        if (responce.isSuccessful) {
            emit(Result.success<SearchCityResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun searchStreet(): Flow<Result<SearchStreetsResponce>> = flow {
        val responce = api.searchStreet()
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