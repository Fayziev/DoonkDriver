package uz.anorgroup.doonkanorgroup.presenter.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkanorgroup.data.request.LoginRequest

interface LoginViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openRegisterFlow: Flow<Unit>
    val openVerifyFlow:Flow<Unit>
    fun login(request: LoginRequest)
}