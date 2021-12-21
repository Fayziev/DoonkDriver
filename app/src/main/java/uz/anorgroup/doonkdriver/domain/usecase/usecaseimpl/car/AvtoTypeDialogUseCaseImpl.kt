package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeAvtoResponce
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.AvtoDialogUseCase
import javax.inject.Inject

class AvtoTypeDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : AvtoDialogUseCase {
    override fun getTransportType(): Flow<Result<TypeOfBodyResponce>> = repository.typesOfBody()
}