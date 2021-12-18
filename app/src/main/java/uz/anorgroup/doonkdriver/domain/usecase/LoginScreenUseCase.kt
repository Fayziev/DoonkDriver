package uz.anorgroup.doonkdriver.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.LoginRequest
import uz.anorgroup.doonkdriver.data.responce.LoginResponse

interface LoginScreenUseCase {

    fun userLogin(authRequest: LoginRequest): Flow<Result<LoginResponse>>

}