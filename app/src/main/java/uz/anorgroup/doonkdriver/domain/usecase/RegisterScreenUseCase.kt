package uz.anorgroup.doonkdriver.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.responce.auth.ContinueResponse

interface RegisterScreenUseCase {
    fun continueSingUp(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>>

}