package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.CarApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.responce.car.*
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.utils.toFormData
import java.io.File
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val api: CarApi, private val pref: MyPref) : CarRepository {


    override fun carCreate(data: CreateCarRequest): Flow<Result<CreateCarResponce>> = flow {
        val response = api.carCreate(data)
        if (response.isSuccessful) {
            emit(Result.success<CreateCarResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)


    override fun brands(): Flow<Result<BrandsResponce>> = flow {
        val response = api.brands()
        if (response.isSuccessful) {
            emit(Result.success<BrandsResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun carModels(): Flow<Result<ModelResponce>> = flow {
        val response = api.carModels()
        if (response.isSuccessful) {
            emit(Result.success<ModelResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)


    override fun typesOfBody(): Flow<Result<TypeOfBodyResponce>> = flow {
        val response = api.typesOfBody()
        if (response.isSuccessful) {
            emit(Result.success<TypeOfBodyResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun avto(): Flow<Result<TypeAvtoResponce>> = flow {
        val response = api.typesOfAvto()
        if (response.isSuccessful) {
            emit(Result.success<TypeAvtoResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun transport(): Flow<Result<TypeTransportsResponce>> = flow {
        val response = api.transportTypes()
        if (response.isSuccessful) {
            emit(Result.success<TypeTransportsResponce>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun getAllCars(): Flow<Result<AllCarsResponse>> = flow {
        val response = api.getCarsInfo()
        if (response.isSuccessful) {
            emit(Result.success<AllCarsResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun uploadImage(file: File): Flow<Result<ImageUploadResponse>> = flow {
        val response = api.uploadImage(file.toFormData())
        if (response.isSuccessful) {
            pref.image = response.body()?.data!!.path
            emit(Result.success<ImageUploadResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Server bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun orderCreate(request: CreateOrderRequest): Flow<Result<CreateOrderResponse>> = flow {
        val response = api.orderCreate(request)
        if (response.isSuccessful) {
            emit(Result.success<CreateOrderResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch { throwable ->
        val errorMessage = Throwable(throwable.message)
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun getAllOrders(): Flow<Result<GetAllOrdersResponse>> = flow {
        val response = api.getAllOrders()
        if (response.isSuccessful) {
            emit(Result.success<GetAllOrdersResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

}

