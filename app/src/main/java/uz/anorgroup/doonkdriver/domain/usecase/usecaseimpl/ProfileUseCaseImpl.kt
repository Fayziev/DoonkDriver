package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl

import uz.anorgroup.doonkdriver.BuildConfig.BASE_URL
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.domain.usecase.car.ProfileUseCase
import javax.inject.Inject


class ProfileUseCaseImpl @Inject constructor(private val pref: MyPref) : ProfileUseCase {

    override fun getName(): String = "${pref.name} ${pref.surname}"

    override fun getImage(): String = "${BASE_URL}${pref.image}"
    override fun setStartScreen(startScreen: Boolean) {
        pref.startScreen = startScreen
    }


}