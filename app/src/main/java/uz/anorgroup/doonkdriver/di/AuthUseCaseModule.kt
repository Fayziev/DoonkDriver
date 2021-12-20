package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.anorgroup.doonkdriver.domain.usecase.auth.LoginScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.auth.RegisterScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.auth.VerifyScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.LoginScreenUseCaseImpl
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.RegisterScreenUseCaseImpl
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.VerifyScreenUseCaseImpl


@Module
@InstallIn(ViewModelComponent::class)
interface AuthUseCaseModule {

    @Binds
    fun getRegisterUseCase(impl: RegisterScreenUseCaseImpl): RegisterScreenUseCase

    @Binds
    fun getVerifyUseCase(impl: VerifyScreenUseCaseImpl): VerifyScreenUseCase

    @Binds
    fun getLoginUseCase(impl: LoginScreenUseCaseImpl): LoginScreenUseCase

}