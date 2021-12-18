package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.auth.ContinueResponse
import uz.anorgroup.doonkdriver.data.responce.auth.LoginResponse
import uz.anorgroup.doonkdriver.data.responce.auth.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.auth.VerifyResponce

interface AuthRepository {

    fun login(request: LoginRequest): Flow<Result<LoginResponse>>
    fun register(request: RegisterRequest): Flow<Result<RegisterResponse>>
    fun verify(request: VerifyRequest): Flow<Result<VerifyResponce>>
    fun confrim(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>>
    fun saveData(phoneNumber: String, name: String, lastName: String)

}