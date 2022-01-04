package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateOrderResponse

interface OrderCreateViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<CreateOrderResponse>
    val openScreenFlow: Flow<Unit>
    val openExpLiveData:LiveData<Unit>

    fun openScreen()
    fun orderCreate(request: CreateOrderRequest)
    fun openExp()
}