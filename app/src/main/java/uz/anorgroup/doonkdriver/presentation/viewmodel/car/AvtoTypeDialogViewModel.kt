package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeAvtoResponce

interface AvtoTypeDialogViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<TypeAvtoResponce>
    val openVerifyFlow: Flow<Unit>

    fun autoTypeRequest()

}