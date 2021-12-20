package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.AddCarDialogUseCase
import javax.inject.Inject

class AddCarDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : AddCarDialogUseCase {
    override fun getBody(): Flow<Result<TypeOfBodyResponce>> = repository.typesOfBody()
}