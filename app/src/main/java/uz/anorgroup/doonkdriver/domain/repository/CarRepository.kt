package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.*

interface CarRepository {

    fun carCreate(data: CreateCarRequest): Flow<Response<CreateCarResponce>>
    fun brands(): Flow<Response<BrandsResponce>>
    fun carModels(): Flow<Response<ModelResponce>>
    fun typesOfTransport(): Flow<Response<TypeTransportResponce>>
    fun typesOfBody(): Flow<Response<TypeOfBodyResponce>>
}