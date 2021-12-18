package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.responce.ContinueResponse
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.usecase.RegisterScreenUseCase
import javax.inject.Inject

class RegisterScreenUseCaseImpl @Inject constructor(private val repository: AuthRepository) : RegisterScreenUseCase {
    override fun continueSingUp(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>> = repository.confrim(request)
}