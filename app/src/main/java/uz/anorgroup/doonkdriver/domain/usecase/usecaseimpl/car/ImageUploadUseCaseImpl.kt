package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ImageUploadResponse
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.usecase.car.ImageUploadUseCase
import java.io.File
import javax.inject.Inject

class ImageUploadUseCaseImpl @Inject constructor(private val repository: CarRepository) : ImageUploadUseCase {
    override fun imageUpload(file: File): Flow<Result<ImageUploadResponse>> = repository.uploadImage(file)
}