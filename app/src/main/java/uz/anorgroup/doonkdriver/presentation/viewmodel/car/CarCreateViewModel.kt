package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce
import java.io.File

interface CarCreateViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<CreateCarResponce>
    val openTruckFlow: Flow<Unit>
    val setBrandFlow: Flow<String>
    val setModelFlow: Flow<String>
    val setYearOfIssueFlow: Flow<String>
    val setColorFlow: Flow<String>
    val setNumberFlow: Flow<String>

    fun setColor(color: String)
    fun setYearOfIssue(yearOfIssue: String)
    fun setNumber(number: String)
    fun setBrand(brand: String)
    fun setModel(model: String)
    fun carCreate(request: CreateCarRequest2)
    fun openScreen()
}