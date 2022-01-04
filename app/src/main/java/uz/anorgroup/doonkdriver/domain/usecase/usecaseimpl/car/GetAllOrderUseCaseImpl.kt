package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.GetAllOrdersResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.GetAllOrdersUseCase
import javax.inject.Inject

class GetAllOrderUseCaseImpl @Inject constructor(private val repository: CarRepository) : GetAllOrdersUseCase {
    override fun getAllOrders(): Flow<Result<GetAllOrdersResponse>> = repository.getAllOrders()
}