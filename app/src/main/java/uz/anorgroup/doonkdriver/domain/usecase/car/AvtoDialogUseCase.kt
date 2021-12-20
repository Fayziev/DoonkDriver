package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeAvtoResponce


interface AvtoDialogUseCase {
    fun getTransportType(): Flow<Result<TypeAvtoResponce>>
}