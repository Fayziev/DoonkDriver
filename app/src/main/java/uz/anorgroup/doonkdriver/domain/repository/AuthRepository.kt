package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.request.LoginRequest
import uz.anorgroup.doonkdriver.data.request.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.ContinueResponse
import uz.anorgroup.doonkdriver.data.responce.LoginResponse
import uz.anorgroup.doonkdriver.data.responce.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.VerifyResponse

interface AuthRepository {

    fun login(request: LoginRequest): Flow<Result<LoginResponse>>
    fun register(request: RegisterRequest): Flow<Result<RegisterResponse>>
    fun verify(request: VerifyRequest): Flow<Result<VerifyResponse>>
    fun confrim(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>>
    fun saveData(phoneNumber: String, name: String, lastName: String)

}