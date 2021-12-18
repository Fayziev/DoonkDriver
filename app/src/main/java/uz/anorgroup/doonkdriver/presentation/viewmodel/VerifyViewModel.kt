package uz.anorgroup.doonkanorgroup.presenter.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkanorgroup.data.request.LoginRequest
import uz.anorgroup.doonkanorgroup.data.request.RegisterRequest
import uz.anorgroup.doonkanorgroup.data.request.VerifyRequest

interface VerifyViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openMainFlow:Flow<Unit>
    fun verify(request: VerifyRequest)
    fun register(request: RegisterRequest)
}