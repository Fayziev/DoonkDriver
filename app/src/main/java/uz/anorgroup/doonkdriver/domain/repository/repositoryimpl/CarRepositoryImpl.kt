package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.CarApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.*
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val api: CarApi, private val pref: MyPref) : CarRepository {


    override fun carCreate(data: CreateCarRequest): Flow<Result<CreateCarResponce>> = flow {
        val responce = api.carCreate(data)
        if (responce.isSuccessful) {
            emit(Result.success<CreateCarResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)


    override fun brands(): Flow<Result<BrandsResponce>> = flow {
        val responce = api.brands()
        if (responce.isSuccessful) {
            emit(Result.success<BrandsResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun carModels(): Flow<Result<ModelResponce>> = flow {
        val responce = api.carModels()
        if (responce.isSuccessful) {
            emit(Result.success<ModelResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun typesOfTransport(): Flow<Result<TypeTransportResponce>> = flow {
        val responce = api.typesOfTransport()
        if (responce.isSuccessful) {
            emit(Result.success<TypeTransportResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun typesOfBody(): Flow<Result<TypeOfBodyResponce>> = flow {
        val responce = api.typesOfBody()
        if (responce.isSuccessful) {
            emit(Result.success<TypeOfBodyResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)
}

