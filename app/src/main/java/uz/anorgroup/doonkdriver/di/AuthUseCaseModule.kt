package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.anorgroup.doonkdriver.domain.usecase.auth.LoginScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.auth.RegisterScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.auth.StartScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.auth.VerifyScreenUseCase
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.LoginScreenUseCaseImpl
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.RegisterScreenUseCaseImpl
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.StartScreenUseCaseImpl
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth.VerifyScreenUseCaseImpl
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthUseCaseModule {

    @Binds
    abstract fun getRegisterUseCase(impl: RegisterScreenUseCaseImpl): RegisterScreenUseCase

    @Binds
    abstract fun getVerifyUseCase(impl: VerifyScreenUseCaseImpl): VerifyScreenUseCase

    @Binds
    abstract fun getLoginUseCase(impl: LoginScreenUseCaseImpl): LoginScreenUseCase

    @Binds
    abstract fun getStartScreenUseCase(impl:StartScreenUseCaseImpl):StartScreenUseCase

}