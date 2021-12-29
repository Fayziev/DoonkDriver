package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.data.responce.car.OrderCreateResponse

interface OrderCreateViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<OrderCreateResponse>

    fun orderCreate(request: OrderCreateRequest)

}