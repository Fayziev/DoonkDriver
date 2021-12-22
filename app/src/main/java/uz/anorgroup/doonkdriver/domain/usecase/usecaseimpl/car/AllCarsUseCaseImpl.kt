package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.AllCarsResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.AllCarsUseCase
import javax.inject.Inject

class AllCarsUseCaseImpl @Inject constructor(private val repository: CarRepository) : AllCarsUseCase {
    override fun getAllCars(): Flow<Result<AllCarsResponse>> = repository.getAllCars()
}