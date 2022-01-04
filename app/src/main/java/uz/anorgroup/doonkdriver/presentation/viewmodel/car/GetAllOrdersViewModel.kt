package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.DataX
import uz.anorgroup.doonkdriver.data.responce.car.GetAllOrdersResponse

interface GetAllOrdersViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<String>
    val getAllOrdersFlow:Flow<DataX>

    fun getAllOrders()
}