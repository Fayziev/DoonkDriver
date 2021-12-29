package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.data.responce.car.OrderCreateResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.OrderCreateUseCase
import javax.inject.Inject

class OrderCreateUseCaseImpl @Inject constructor(private val repository: CarRepository) : OrderCreateUseCase {
    override fun orderCreate(request: OrderCreateRequest): Flow<Result<OrderCreateResponse>> = repository.orderCreate(request)
}