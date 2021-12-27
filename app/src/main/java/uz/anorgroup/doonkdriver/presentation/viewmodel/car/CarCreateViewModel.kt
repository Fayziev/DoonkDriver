package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce

interface CarCreateViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<CreateCarResponce>
    val openTruckFlow: Flow<Unit>

    fun carCreate(request: CreateCarRequest2)
}