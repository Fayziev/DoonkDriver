package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ModelResponce


interface ModelsDialogUseCase {
    fun getModels(): Flow<Result<ModelResponce>>
}