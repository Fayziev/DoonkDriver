package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.data.responce.car.OrderCreateResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import javax.inject.Inject

interface OrderCreateUseCase {
    fun orderCreate(request: OrderCreateRequest): Flow<Result<OrderCreateResponse>>
}