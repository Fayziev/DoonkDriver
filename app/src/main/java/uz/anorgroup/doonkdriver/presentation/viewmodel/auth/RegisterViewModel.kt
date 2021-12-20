package uz.anorgroup.doonkdriver.presentation.viewmodel.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest

interface RegisterViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<Unit>
    val openVerifyFlow: Flow<Unit>
    fun continueSignUpRequest(request: ContinueSignUpRequest)
}