package uz.gita.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.doonkdriver.data.request.ContinueSignUpRequest
import uz.gita.doonkdriver.data.request.LoginRequest
import uz.gita.doonkdriver.data.request.RegisterRequest
import uz.gita.doonkdriver.data.request.VerifyRequest
import uz.gita.doonkdriver.data.responce.ContinueResponse
import uz.gita.doonkdriver.data.responce.LoginResponse
import uz.gita.doonkdriver.data.responce.RegisterResponse
import uz.gita.doonkdriver.data.responce.VerifyResponse

interface AuthApi {

    @POST("signIn")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("signUp")
    suspend fun register(@Body data: RegisterRequest): Response<RegisterResponse>

    @POST("confirm")
    suspend fun continueSingUp(@Body data: ContinueSignUpRequest): Response<ContinueResponse>

    @POST("verify")
    suspend fun verifyCode(@Body data: VerifyRequest): Response<VerifyResponse>
}