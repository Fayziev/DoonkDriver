package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.auth.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.auth.VerifyResponce
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.usecase.VerifyScreenUseCase
import javax.inject.Inject

class VerifyScreenUseCaseImpl @Inject constructor(private val repository: AuthRepository) : VerifyScreenUseCase {

    override fun register(request: RegisterRequest): Flow<Result<RegisterResponse>> = repository.register(request)
    override fun saveData(phoneNumber: String, name: String, lastName: String) {
        repository.saveData(phoneNumber, name, lastName)
    }

    override fun sendSmsVerify(request: VerifyRequest): Flow<Result<VerifyResponce>> = repository.verify(request)
}