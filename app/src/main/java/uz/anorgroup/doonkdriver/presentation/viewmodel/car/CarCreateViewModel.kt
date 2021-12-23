package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.BrandsResponce
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce

interface CarCreateViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<CreateCarResponce>
    val openVerifyFlow: Flow<Unit>

    fun carCreate(request: CreateCarRequest)
}