package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenCarAddBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.BrandsBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.ModelBottomDialog
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class AddCarScreen : Fragment(R.layout.screen_car_add) {
    private val bind by viewBinding(ScreenCarAddBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {

        tipTransportaLine.setOnClickListener {
            val dialog = BrandsBottomDialog()
            dialog.setListener {
                textMarka.text = it
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "brands")
        }

        tipKuzovaLine.setOnClickListener {
            val dialog = ModelBottomDialog()
            dialog.setListener {
                textModel.text = it
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "models")
        }

        saveBtn.setOnClickListener {

        }
    }
}