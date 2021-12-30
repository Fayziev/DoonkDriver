package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.CarApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.data.responce.car.*
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.utils.toFormData
import java.io.File
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val api: CarApi, private val pref: MyPref) : CarRepository {


    override fun carCreate(data: CreateCarRequest2): Flow<Result<CreateCarResponce>> = flow {
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

    override fun avto(): Flow<Result<TypeAvtoResponce>> = flow {
        val responce = api.typesOfAvto()
        if (responce.isSuccessful) {
            emit(Result.success<TypeAvtoResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun transport(): Flow<Result<TypeTransportsResponce>> = flow {
        val responce = api.transportTypes()
        if (responce.isSuccessful) {
            emit(Result.success<TypeTransportsResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun getAllCars(): Flow<Result<AllCarsResponse>> = flow {
        val responce = api.getCarsInfo()
        if (responce.isSuccessful) {
            emit(Result.success<AllCarsResponse>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun uploadImage(file: File): Flow<Result<ImageUploadResponse>> = flow {

        val response = api.uploadImage(file.toFormData())
        if (response.isSuccessful) {
            emit(Result.success<ImageUploadResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Server bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun orderCreate(request: OrderCreateRequest): Flow<Result<OrderCreateResponse>> = flow{
        val response = api.orderCreate(request)
        if (response.isSuccessful) {
            emit(Result.success<OrderCreateResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Server bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

}

