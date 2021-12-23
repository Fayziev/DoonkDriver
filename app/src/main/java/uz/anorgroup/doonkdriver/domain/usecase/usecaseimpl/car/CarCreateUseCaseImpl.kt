package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.CarCreateUseCase
import javax.inject.Inject

class CarCreateUseCaseImpl @Inject constructor(private val repository: CarRepository) : CarCreateUseCase {
    override fun createCar(request: CreateCarRequest): Flow<Result<CreateCarResponce>> = repository.carCreate(request)
}