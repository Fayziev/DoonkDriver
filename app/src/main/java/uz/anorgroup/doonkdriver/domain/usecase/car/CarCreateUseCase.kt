package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce

interface CarCreateUseCase {
    fun createCar(request: CreateCarRequest): Flow<Result<CreateCarResponce>>
}