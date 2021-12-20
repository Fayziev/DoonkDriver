package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce


interface AddCarDialogUseCase {
    fun getBody(): Flow<Result<TypeOfBodyResponce>>
}