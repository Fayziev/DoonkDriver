package uz.anorgroup.doonkdriver.domain.usecase.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ImageUploadResponse
import java.io.File

interface ImageUploadUseCase {
    fun imageUpload(file:File):Flow<Result<ImageUploadResponse>>
}