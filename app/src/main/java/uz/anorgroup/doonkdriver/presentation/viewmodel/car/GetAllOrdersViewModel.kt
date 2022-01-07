package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.DataX
import uz.anorgroup.doonkdriver.data.responce.car.GetAllOrdersResponse

interface GetAllOrdersViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<String>
    val getAllOrdersFlow:Flow<DataX>
    val openScreenFlow:LiveData<Unit>
    val openScreenPassFlow:LiveData<Unit>
    val logoutLiveData:LiveData<Unit>

    fun logout()
    fun openScreen()
    fun openScreenPass()
    fun getAllOrders()
}