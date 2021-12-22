package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.AllCarsResponse

interface AllCarsUseCase {
    fun getAllCars(): Flow<Result<AllCarsResponse>>
}