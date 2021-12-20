package uz.anorgroup.doonkdriver.presentation.viewmodel.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest

interface LoginViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openRegisterFlow: Flow<Unit>
    val openVerifyFlow: Flow<Unit>
    fun login(request: LoginRequest)
}