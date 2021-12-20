package uz.anorgroup.doonkdriver.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.auth.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.auth.VerifyResponce


interface VerifyScreenUseCase {
    fun sendSmsVerify(request: VerifyRequest): Flow<Result<VerifyResponce>>
    fun register(request: RegisterRequest): Flow<Result<RegisterResponse>>
    fun saveData(phoneNumber: String, name: String, lastName: String)
}