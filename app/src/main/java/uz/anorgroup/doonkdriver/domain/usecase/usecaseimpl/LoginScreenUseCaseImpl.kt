package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.data.responce.auth.LoginResponse
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.usecase.LoginScreenUseCase
import javax.inject.Inject

class LoginScreenUseCaseImpl @Inject constructor(private val repositoryImpl: AuthRepository) : LoginScreenUseCase {
    override fun userLogin(authRequest: LoginRequest): Flow<Result<LoginResponse>> = repositoryImpl.login(authRequest)
}