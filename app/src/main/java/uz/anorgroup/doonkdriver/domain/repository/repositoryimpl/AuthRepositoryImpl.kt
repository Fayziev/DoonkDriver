package uz.anorgroup.doonkdriver.domain.repository.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.doonkdriver.data.api.AuthApi
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.auth.ContinueResponse
import uz.anorgroup.doonkdriver.data.responce.auth.LoginResponse
import uz.anorgroup.doonkdriver.data.responce.auth.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.auth.VerifyResponce
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: AuthApi, private val pref: MyPref) : AuthRepository {

    override fun login(request: LoginRequest): Flow<Result<LoginResponse>> = flow {
        val responce = api.login(request)
        if (responce.isSuccessful) {
            emit(Result.success<LoginResponse>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun confirm(request: ContinueSignUpRequest): Flow<Result<ContinueResponse>> = flow {
        val responce = api.continueSingUp(request)
        if (responce.isSuccessful) {
            emit(Result.success<ContinueResponse>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun saveData(phoneNumber: String, name: String, lastName: String) {
        pref.name = name
        pref.phoneNumber = phoneNumber
        pref.surname
    }


    override fun getStartScreen(): Boolean = pref.startScreen

    override fun register(request: RegisterRequest): Flow<Result<RegisterResponse>> = flow {
        val response = api.register(request)
        if (response.isSuccessful) {
            response.body()?.data?.let {
                pref.accessToken = it.token
                pref.phoneNumber = request.phone
                pref.startScreen = true
                pref.name = request.first_name
                pref.surname = request.last_name
            }
            emit(Result.success<RegisterResponse>(response.body()!!))
        } else {
            emit(Result.failure(Throwable(response.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

    override fun verify(request: VerifyRequest): Flow<Result<VerifyResponce>> = flow {
        val responce = api.verifyCode(request)
        if (responce.isSuccessful) {
            responce.body()?.data.let {
                if (it != null) {
                    pref.accessToken = it.token
                    pref.startScreen = true
                    pref.phoneNumber = it.phone
                    pref.name = it.firstName
                    pref.surname = it.lastName
                }
            }
            emit(Result.success<VerifyResponce>(responce.body()!!))
        } else {
            emit(Result.failure(Throwable(responce.errorBody().toString())))
        }
    }.catch {
        val errorMessage = Throwable("Sever bilan muammo bo'ldi")
        emit(Result.failure(errorMessage))
    }.flowOn(Dispatchers.IO)

}