package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.CreateOrderResponse

interface ProfileViewModel {

    val openScreenFlow: Flow<Unit>
    val getNameFlow:Flow<String>
    val getImageFlow:Flow<String>
    fun getName()
    fun getImage()
    fun openScreen()
    fun setStartScreen(startScreen:Boolean)
}