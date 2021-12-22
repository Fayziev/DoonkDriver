package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.*

interface CarRepository {
    fun carCreate(data: CreateCarRequest): Flow<Result<CreateCarResponce>>
    fun brands(): Flow<Result<BrandsResponce>>
    fun carModels(): Flow<Result<ModelResponce>>
    fun typesOfBody(): Flow<Result<TypeOfBodyResponce>>
    fun avto(): Flow<Result<TypeAvtoResponce>>
    fun transport(): Flow<Result<TypeTransportsResponce>>
    fun getAllCars():Flow<Result<AllCarsResponse>>

}