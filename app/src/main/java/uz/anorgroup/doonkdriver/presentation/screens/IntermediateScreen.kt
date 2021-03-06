package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.databinding.ScreenIntermediateBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AddAdapter
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CarCreateViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class IntermediateScreen : Fragment(R.layout.screen_intermediate) {
    private val bind by viewBinding(ScreenIntermediateBinding::bind)
    private val adapter = AddAdapter()
    private val viewModel: CarCreateViewModel by viewModels<CarCreateViewModelImpl>()
    private lateinit var bundle2: Bundle
    private lateinit var data: CreateCarRequest
    private lateinit var listLocation: ArrayList<CarSeet>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openTruckFlow.onEach {
            findNavController().navigate(R.id.mainScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        bundle2 = requireArguments()
        data = bundle2.getParcelable("data")!!
        listLocation = ArrayList<CarSeet>()
        data.carSeet?.let { listLocation.addAll(it) }
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
                    listLocation.add(CarSeet(position, qty))
                    val dataNew =
                        CreateCarRequest(
                            data.brand,
                            data.carModel,
                            data.color,
                            data.yearOfIssue,
                            data.photos,
                            data.typeOfBody,
                            data.typeOfTransport,
                            data.liftingCapacity,
                            data.weight,
                            listLocation
                        )
                    viewModel.openScreen()
                    viewModel.carCreate(dataNew)
                    viewModel.progressFlow.onEach {
                        if (it) progress.show()
                        else progress.hide()
                    }.launchIn(lifecycleScope)
                    viewModel.successFlow.onEach {
                        showToast("Success")
                    }.launchIn(lifecycleScope)
                    viewModel.errorFlow.onEach {
                        showToast("Error")
                    }.launchIn(lifecycleScope)
                }
            } else {
                val dataNew =
                    CreateCarRequest(
                        data.brand,
                        data.carModel,
                        data.color,
                        data.yearOfIssue,
                        data.photos,
                        data.typeOfBody,
                        data.typeOfTransport,
                        data.liftingCapacity,
                        data.weight,
                        data.carSeet
                    )
                viewModel.carCreate(dataNew)
                viewModel.openScreen()
                viewModel.progressFlow.onEach {
                    if (it) progress.show()
                    else progress.hide()
                }.launchIn(lifecycleScope)
                viewModel.successFlow.onEach {
                    showToast("Success")
                }.launchIn(lifecycleScope)
                viewModel.errorFlow.onEach {
                    showToast("Error")
                }.launchIn(lifecycleScope)
            }

        }
    }
}