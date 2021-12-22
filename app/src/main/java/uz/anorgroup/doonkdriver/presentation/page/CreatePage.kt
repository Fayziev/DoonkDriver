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

@AndroidEntryPoint
class CreatePage : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        nextBt.setOnClickListener {
            if (whereCity.text.isEmpty() && whereStreet.text.isEmpty()
                && directionsStreet.text.isEmpty() && directionsCity.text.isEmpty()
            ) {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            } else {
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
            }

        }

        val bundle = Bundle()
        var id1 = "0"
        var id2 = "0"

        bind.whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                id1 = it.id.toString()
                bind.whereCity.text = it.name
                bundle.putString("id", id1)
                if (id1 == it.id.toString()) {
                    dialog.dismiss()
                }
            }
            dialog.show(childFragmentManager, "CityDialog")
        }

        bind.whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.whereStreet.text = it.name
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }


        bind.directionsCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.directionsCity.text = it.name
                id2 = it.id.toString()
                bundle.putString("id", id2)
                if (id2 == it.id.toString()) {
                    dialog.dismiss()
                }
            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        bind.directionsStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.directionsStreet.text = it.name
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")

        }

    }
}