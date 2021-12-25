package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.BrandsResponce
import uz.anorgroup.doonkdriver.data.responce.car.ImageUploadResponse
import java.io.File

interface ImageUploadViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<ImageUploadResponse>

    fun imageUpload(file:File)
}