package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.BrandsResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.BrandsDialogUseCase
import javax.inject.Inject

class BrandsTypeDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : BrandsDialogUseCase {
    override fun getTransportType(): Flow<Result<BrandsResponce>> = repository.brands()
}