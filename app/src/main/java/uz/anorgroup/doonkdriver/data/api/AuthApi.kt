package uz.anorgroup.doonkdriver.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.data.request.auth.RegisterRequest
import uz.anorgroup.doonkdriver.data.request.auth.VerifyRequest
import uz.anorgroup.doonkdriver.data.responce.auth.ContinueResponse
import uz.anorgroup.doonkdriver.data.responce.auth.LoginResponse
import uz.anorgroup.doonkdriver.data.responce.auth.RegisterResponse
import uz.anorgroup.doonkdriver.data.responce.auth.VerifyResponce

interface AuthApi {

    @POST("signIn")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("signUp")
    suspend fun register(@Body data: RegisterRequest): Response<RegisterResponse>

    @POST("confirm")
    suspend fun continueSingUp(@Body data: ContinueSignUpRequest): Response<ContinueResponse>

    @POST("verify")
    suspend fun verifyCode(@Body data: VerifyRequest): Response<VerifyResponce>
}