package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.doonkdriver.domain.usecase.car.AddCarDialogUseCase
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car.AddCarDialogUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface CarUseCaseModule {

    @Binds
    fun getAddCardBtDialog(impl: AddCarDialogUseCaseImpl): AddCarDialogUseCase

}