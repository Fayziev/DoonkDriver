package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateOrderResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.OrderCreateUseCase
import javax.inject.Inject

class OrderCreateUseCaseImpl @Inject constructor(private val repository: CarRepository) : OrderCreateUseCase {
    override fun orderCreate(request: CreateOrderRequest): Flow<Result<CreateOrderResponse>> {
        return  repository.orderCreate(request)
    }
}