package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.doonkdriver.domain.usecase.car.*
import uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.ProfileUseCaseImpl
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

    @Binds
    fun getAllCars(impl: AllCarsUseCaseImpl): AllCarsUseCase

    @Binds
    fun carCreate(impl: CarCreateUseCaseImpl): CarCreateUseCase

    @Binds
    fun uploadImage(impl: ImageUploadUseCaseImpl): ImageUploadUseCase

    @Binds
    fun orderCreate(impl: OrderCreateUseCaseImpl): OrderCreateUseCase

    @Binds
    fun getAllOrders(impl: GetAllOrderUseCaseImpl): GetAllOrdersUseCase

    @Binds
    fun getProfile(impl: ProfileUseCaseImpl): ProfileUseCase

}