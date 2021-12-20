package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.BodyDialogUseCase
import javax.inject.Inject

class BodyDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : BodyDialogUseCase {
    override fun getBody(): Flow<Result<TypeOfBodyResponce>> = repository.typesOfBody()
}