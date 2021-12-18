package uz.anorgroup.doonkdriver.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.VerifyResponce


interface VerifyScreenUseCase {
    fun sendSmsVerify(request: VerifyRequest): Flow<Result<VerifyResponce>>
    fun register(request: RegisterRequest): Flow<Result<RegisterResponse>>
    fun saveData(phoneNumber: String, name: String, lastName: String)
}