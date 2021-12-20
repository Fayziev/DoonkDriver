package uz.anorgroup.doonkdriver.presentation.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.BrandsResponce

interface BrandTypeDialogViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<BrandsResponce>
    val openVerifyFlow: Flow<Unit>

    fun continueSignUpRequest()

}