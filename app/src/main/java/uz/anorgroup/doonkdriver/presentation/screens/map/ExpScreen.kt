package uz.anorgroup.doonkdriver.presentation.screens.map

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ExperimentScreenBinding

@AndroidEntryPoint
class ExpScreen : Fragment(R.layout.experiment_screen) {
    private val bind by viewBinding(ExperimentScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()
        bind.adress.text = bundle.getParcelable<Parcelable>("value").toString()
    }
}