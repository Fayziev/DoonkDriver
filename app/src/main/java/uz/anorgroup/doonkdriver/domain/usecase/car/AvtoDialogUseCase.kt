package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeAvtoResponce
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce


interface AvtoDialogUseCase {
    fun getTransportType(): Flow<Result<TypeOfBodyResponce>>
}