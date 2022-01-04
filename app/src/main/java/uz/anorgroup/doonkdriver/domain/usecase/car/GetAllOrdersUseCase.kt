package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.GetAllOrdersResponse

interface GetAllOrdersUseCase {
    fun getAllOrders(): Flow<Result<GetAllOrdersResponse>>
}