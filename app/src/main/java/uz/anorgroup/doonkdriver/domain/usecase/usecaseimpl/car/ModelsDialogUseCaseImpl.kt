package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ModelResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.ModelsDialogUseCase
import javax.inject.Inject

class ModelsDialogUseCaseImpl @Inject constructor(private val carRepository: CarRepository) : ModelsDialogUseCase {
    override fun getModels(): Flow<Result<ModelResponce>> = carRepository.carModels()

}