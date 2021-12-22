package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.doonkdriver.domain.usecase.car.*
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CarUseCaseModule {

    @Binds
    abstract fun getBody(impl: BodyDialogUseCaseImpl): BodyDialogUseCase

    @Binds
    abstract fun getAvto(impl: AvtoTypeDialogUseCaseImpl): AvtoDialogUseCase

    @Binds
    abstract fun getTransport(impl: TypeTransportDialogUseCaseImpl): TypeTransportUseCase

    @Binds
    abstract fun getModels(impl: ModelsDialogUseCaseImpl): ModelsDialogUseCase

    @Binds
    abstract fun getBrands(impl: BrandsTypeDialogUseCaseImpl): BrandsDialogUseCase

    @Binds
    abstract fun getAllCars(impl:AllCarsUseCaseImpl):AllCarsUseCase

}