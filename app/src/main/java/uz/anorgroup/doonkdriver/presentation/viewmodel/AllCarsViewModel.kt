package uz.anorgroup.doonkdriver.presentation.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.AllCarsResponse

interface AllCarsViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<AllCarsResponse>
    val openAddCarFlow: Flow<Unit>
    val openCreateOrderFlow:Flow<Unit>

    fun getAllCars()
    fun openScreen()
    fun openCreateOrder()
}