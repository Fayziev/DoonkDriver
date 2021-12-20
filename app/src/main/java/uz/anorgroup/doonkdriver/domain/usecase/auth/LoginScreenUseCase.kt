package uz.anorgroup.doonkdriver.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.data.responce.auth.LoginResponse

interface LoginScreenUseCase {
    fun userLogin(authRequest: LoginRequest): Flow<Result<LoginResponse>>
}