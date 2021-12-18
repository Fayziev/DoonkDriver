package uz.anorgroup.doonkanorgroup.presenter.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkanorgroup.data.request.ContinueSignUpRequest
import uz.anorgroup.doonkanorgroup.data.request.LoginRequest
import uz.anorgroup.doonkanorgroup.data.request.RegisterRequest

interface RegisterViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openVerifyFlow:Flow<Unit>
    fun continueSignUpRequest(request: ContinueSignUpRequest)
}