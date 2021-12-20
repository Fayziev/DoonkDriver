package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.TypeOfBodyResponce
import uz.anorgroup.doonkdriver.data.responce.car.TypeTransportResponce
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.TransportDialogUseCase
import javax.inject.Inject

class TransportTypeDialogUseCaseImpl @Inject constructor(private val repository: CarRepository) : TransportDialogUseCase {
    override fun getTransportType(): Flow<Result<TypeTransportResponce>> = repository.typesOfTransport()
}