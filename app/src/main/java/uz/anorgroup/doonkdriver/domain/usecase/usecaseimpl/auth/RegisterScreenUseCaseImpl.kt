package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.responce.auth.ContinueResponse
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.usecase.auth.RegisterScreenUseCase
import javax.inject.Inject

class RegisterScreenUseCaseImpl @Inject constructor(private val repository: AuthRepository) : RegisterScreenUseCase {
    override fun continueSingUp(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>> = repository.confirm(request)
}