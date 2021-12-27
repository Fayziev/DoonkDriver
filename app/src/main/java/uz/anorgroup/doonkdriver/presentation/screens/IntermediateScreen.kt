package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.databinding.ScreenIntermediateBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AddAdapter
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class IntermediateScreen : Fragment(R.layout.screen_intermediate) {
    private val bind by viewBinding(ScreenIntermediateBinding::bind)
    private val adapter = AddAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle2 = requireArguments()
        val data: CreateCarRequest = bundle2.getParcelable("data")!!
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        var qty1 = -1
        var position1 = -1
        var qty2 = -1
        var position2 = -1
        val listLocation = ArrayList<CarSeet>()
        data.carSeet?.let { listLocation.addAll(it) }
        var bool = false
//        addCarBt.setOnClickListener {
//            addCarBt.visibility = View.GONE
//            whereCity2.visibility = View.VISIBLE
//            whereStreet2.visibility = View.VISIBLE
//            back2.visibility = View.VISIBLE
//            line2.visibility = View.VISIBLE
//            location2.visibility = View.VISIBLE
//            whereText2.visibility = View.VISIBLE
//            nextBt.text = "Далее"
//            bool = true
//        }
        val list = ArrayList<LocationAddData>()
        var c=0
        addCarBt.setOnClickListener {
            list.add(LocationAddData("${c++}", ""))
            showToast(list.size.toString())
            adapter.notifyItemInserted(list.size)
            adapter.submitList(list)
        }
        nextBt.setOnClickListener {

//            if (bool) {
//                if (qty1 != -1 && position1 != -1 && qty2 != -1 && position2 != -1) {
//                    listLocation.add(CarSeet(position1, qty1))
//                    listLocation.add(CarSeet(position2, qty2))
//                    val dataCar = CreateCarRequest(listLocation)
//                    val bundle = Bundle()
//                    bundle.putParcelable("data", dataCar)
//                    error1.visibility = View.GONE
//                    error2.visibility = View.GONE
//                    findNavController().navigate(R.id.action_screenIntermediate_to_whenScreen, bundle)
//                } else {
//                    error1.visibility = View.VISIBLE
//                    error2.visibility = View.VISIBLE
//                }
//            } else {
//                if (qty1 != -1 && position1 != -1) {
//                    listLocation.add(CarSeet(position1, qty1))
//                    val newData = CreateCarRequest(listLocation)
//                    val bundle = Bundle()
//                    bundle.putParcelable("data", newData)
//                    error1.visibility = View.GONE
//                    findNavController().navigate(R.id.truckAddScreen, bundle)
//                } else {
//                    error1.visibility = View.VISIBLE
//                }
//            }
        }

        val bundle = Bundle()
        var id1 = "0"
        var id2 = "0"
        bind.whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.whereCity.text = it.name
                id1 = it.id.toString()
                bundle.putString("id", id1)
                position1 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "CityDialog")
        }

        bind.whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.whereStreet.text = it.name
                qty1 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

        bind.whereCity2.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.whereCity2.text = it.name
                id2 = it.id.toString()
                position2 = it.id
                bundle.putString("id", id2)
                dialog.dismiss()

            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        bind.whereStreet2.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.whereStreet2.text = it.name
                qty2 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

    }
}