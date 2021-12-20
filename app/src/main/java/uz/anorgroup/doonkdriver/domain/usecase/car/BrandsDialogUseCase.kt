package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.BrandsResponce


interface BrandsDialogUseCase {
    fun getTransportType(): Flow<Result<BrandsResponce>>
}