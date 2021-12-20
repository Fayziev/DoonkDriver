package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeTransportsResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.TypeTransportUseCase
import javax.inject.Inject

class TypeTransportDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : TypeTransportUseCase {
    override fun getTypeTransport(): Flow<Result<TypeTransportsResponce>> = repository.typesTransport()
}