package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.doonkdriver.domain.usecase.car.*
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car.*

@Module
@InstallIn(SingletonComponent::class)
interface CarUseCaseModule {

    @Binds
    fun getBody(impl: BodyDialogUseCaseImpl): BodyDialogUseCase

    @Binds
    fun getAvto(impl: AvtoTypeDialogUseCaseImpl): AvtoDialogUseCase

    @Binds
    fun getTransport(impl: TypeTransportDialogUseCaseImpl): TypeTransportUseCase

    @Binds
    fun getModels(impl: ModelsDialogUseCaseImpl): ModelsDialogUseCase

    @Binds
    fun getBrands(impl: BrandsTypeDialogUseCaseImpl): BrandsDialogUseCase

}