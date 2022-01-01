package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.MapApi
import uz.anorgroup.doonkdriver.data.responce.map.MapResponse
import uz.anorgroup.doonkdriver.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(private val api: MapApi) : MapRepository {

    override fun getDirection(origin: String, destination: String, key: String): Flow<Result<MapResponse>> = flow {
        val response = api.getDirection(origin, destination, key)
        if (response.isSuccessful) {
            emit(Result.success<MapResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.flowOn(Dispatchers.IO)
}