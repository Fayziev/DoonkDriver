package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.data.responce.car.TypeTransportsResponce

interface TypeTransportUseCase {
    fun getTypeTransport(): Flow<Result<TypeTransportsResponce>>
}