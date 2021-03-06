package uz.anorgroup.doonkdriver.presentation.viewmodel.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest

interface VerifyViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openMainFlow: Flow<Unit>
    fun verify(request: VerifyRequest)
    fun register(request: RegisterRequest)
}