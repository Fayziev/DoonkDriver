package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.databinding.ScreenSignatureBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class ParcelInfoScreen : Fragment(R.layout.screen_signature) {
    private val binding by viewBinding(ScreenSignatureBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        val bundle = requireArguments()
        val data = bundle.getSerializable("parcel") as Parcel
        data.type.let {
            type.text = it.toString()
        }
        data.weight.let {
            weight.text = "$it kg"
        }
        if (data.width.toString().isNotEmpty()
            && data.length.toString().isNotEmpty()
            && data.height.toString().isNotEmpty()
        ) {
            size.text = "${data.width}cm X ${data.length}cm X ${data.height}cm"
        } else {
            size.text = "${data.width}cm X ${data.length}cm X ${data.height}cm"
        }

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}