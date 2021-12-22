package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class CreatePage : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        nextBt.setOnClickListener {
            if (whereCity.text.isEmpty() && whereStreet.text.isEmpty()
                && directionsStreet.text.isEmpty() && directionsCity.text.isEmpty()) {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            } else {
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
            }
        var id1 = "1"
        var id2 = "2"
        bind.nextBt.setOnClickListener {
//            if (whereCity.text.isEmpty() && directionsCity.text.isEmpty()
//                && whereStreet.text.isEmpty() && directionsStreet.text.isEmpty()
//            ) {
//                error1.visibility = View.VISIBLE
//                error2.visibility = View.VISIBLE
//            } else {
//                error1.visibility = View.GONE
//                error2.visibility = View.GONE
//                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
//            }
            findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
        }
        val bundle = Bundle()
        bind.whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.whereCity.text = it.name
                id1 = (it.id + 1).toString()
                showToast("${it.id}-- Qurash akani Id si")
                dialog.dismiss()
            }
            bundle.putString("id", id1)
            dialog.show(childFragmentManager, "CityDialog")
        }

        bind.whereStreet.setOnClickListener {

            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.whereStreet.text = it
                dialog.dismiss()
            }

            dialog.show(childFragmentManager, "StreetDialog")
        }
        bind.directionsCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.directionsCity.text = it.name
                id2 = it.id.toString()
                dialog.dismiss()
                showToast("${it.id}-- Qurash akani Id si")
            }
            bundle.putString("id", id2)
            dialog.show(childFragmentManager, "CityDialog")
        }
        bind.directionsStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.directionsStreet.text = it
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")

        }
    }

}