package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.data.responce.car.*
import java.io.File

interface CarRepository {

    fun carCreate(data: CreateCarRequest2): Flow<Result<CreateCarResponce>>

    fun brands(): Flow<Result<BrandsResponce>>

    fun carModels(): Flow<Result<ModelResponce>>

    fun typesOfBody(): Flow<Result<TypeOfBodyResponce>>

    fun avto(): Flow<Result<TypeAvtoResponce>>

    fun transport(): Flow<Result<TypeTransportsResponce>>

    fun getAllCars(): Flow<Result<AllCarsResponse>>

    fun uploadImage(file: File): Flow<Result<ImageUploadResponse>>

    fun orderCreate(request: OrderCreateRequest): Flow<Result<OrderCreateResponse>>

}