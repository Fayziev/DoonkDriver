package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.anorgroup.doonkdriver.data.api.CarApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.*
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val api: CarApi, private val pref: MyPref) : CarRepository {
    override fun carCreate(data: CreateCarRequest): Flow<Response<CreateCarResponce>> {
        TODO("Not yet implemented")
    }

    override fun brands(): Flow<Response<BrandsResponce>> {
        TODO("Not yet implemented")
    }

    override fun carModels(): Flow<Response<ModelResponce>> {
        TODO("Not yet implemented")
    }

    override fun typesOfTransport(): Flow<Response<TypeTransportResponce>> {
        TODO("Not yet implemented")
    }

    override fun typesOfBody(): Flow<Response<TypeOfBodyResponce>> {
        TODO("Not yet implemented")
    }
}