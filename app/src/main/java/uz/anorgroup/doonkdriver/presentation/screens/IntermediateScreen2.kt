package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.data.request.car.Addres
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.databinding.ScreenIntermediateBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AddAdapter
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreateOrderViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class IntermediateScreen2 : Fragment(R.layout.screen_intermediate) {
    private val bind by viewBinding(ScreenIntermediateBinding::bind)
    private val adapter = AddAdapter()
    private val viewModel: CreateOrderViewModel by viewModels<CreateOrderViewModelImpl>()
    private lateinit var listLocation: ArrayList<Addres>

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle2 = requireArguments()
        val data = bundle2.getParcelable<Parcelable>("data2") as CreateOrderRequest
        listLocation = ArrayList()
        data.address.let { it?.let { it1 -> listLocation.addAll(it1) } }
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = Bundle()
        var id = "0"
        var qty = -1
        var position = -1
        var bool = true

        adapter.setCityListener { pos ->
            val dialog = CitysBottomDialog()
            dialog.setListener {
                id = it.id.toString()
                bundle.putString("id", id)
                position = it.id
                dialog.dismiss()
                val mutableList = adapter.currentList.toMutableList()
                mutableList[pos] = mutableList[pos].copy(city = it.name)
                adapter.submitList(mutableList)
            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        adapter.setStreetListener { pos ->
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                qty = it.id
                dialog.dismiss()
                val mutableList = adapter.currentList.toMutableList()
                mutableList[pos] = mutableList[pos].copy(street = it.name)
                adapter.submitList(mutableList)
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }
        adapter.setDeleteListener {
            val mutableList = adapter.currentList.toMutableList()
            mutableList.removeAt(it)
            adapter.submitList(mutableList)
        }

        addCarBt.setOnClickListener {
            val mutableList = adapter.currentList.toMutableList()
            mutableList.add(LocationAddData(adapter.currentList.size, "", ""))
            adapter.submitList(mutableList)
            if (bool) {
                nextBt.text = requireContext().getString(R.string.dalee)
                bool = false
            }
        }
        nextBt.setOnClickListener {
            if (!bool) {
                if (position != -1 && qty != -1) {
                    listLocation.add(Addres(position, qty))
                    val dataNew = CreateOrderRequest(
                        data.car,
                        listLocation
                    )
                    val bundleNew = Bundle()
                    bundleNew.putParcelable("data2", dataNew)
                    viewModel.openScreen()
                    viewModel.openScreenLiveData.observe(this@IntermediateScreen2, {
                        findNavController().navigate(R.id.action_intermediateScreen2_to_whenScreen, bundleNew)
                    })
                }
            } else {
                val dataNew =
                    CreateOrderRequest(
                        data.car,
                        data.address
                    )
                val bundleNew = Bundle()
                bundleNew.putParcelable("data2", dataNew)
                viewModel.openScreen()
                viewModel.openScreenLiveData.observe(this@IntermediateScreen2, {
                    findNavController().navigate(R.id.action_intermediateScreen2_to_whenScreen, bundleNew)
                })
            }

        }
    }
}