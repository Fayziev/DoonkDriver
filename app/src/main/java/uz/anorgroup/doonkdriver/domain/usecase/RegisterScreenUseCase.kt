package uz.anorgroup.doonkdriver.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.responce.ContinueResponse

interface RegisterScreenUseCase {
    fun continueSingUp(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>>

}