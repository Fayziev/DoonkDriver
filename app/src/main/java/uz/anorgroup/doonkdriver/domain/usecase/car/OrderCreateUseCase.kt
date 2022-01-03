package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateOrderResponse

interface OrderCreateUseCase {
    fun orderCreate(request: CreateOrderRequest): Flow<Result<CreateOrderResponse>>
}