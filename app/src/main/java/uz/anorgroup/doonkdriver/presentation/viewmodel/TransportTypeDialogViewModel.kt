package uz.anorgroup.doonkdriver.presentation.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.data.responce.car.TypeTransportResponce

interface TransportTypeDialogViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<TypeTransportResponce>
    val openVerifyFlow: Flow<Unit>

    fun continueSignUpRequest()

}