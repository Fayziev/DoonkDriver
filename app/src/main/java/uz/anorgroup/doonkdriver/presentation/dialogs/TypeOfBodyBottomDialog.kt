package uz.anorgroup.doonkdriver.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.ModelData
import uz.anorgroup.doonkdriver.databinding.BottomDialogMarksBinding
import uz.anorgroup.doonkdriver.databinding.BottomDialogTypeLoadingBinding
import uz.anorgroup.doonkdriver.presentation.adapters.ModelTypeAdapter2
import uz.anorgroup.doonkdriver.presentation.adapters.TypeOfBodyAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.BodyBtDialogViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.BodyBtDialogViewModelImpl
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.ModelsDialogViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class TypeOfBodyBottomDialog : BottomSheetDialogFragment() {
    private val viewModel:BodyBtDialogViewModel by viewModels<BodyBtDialogViewModelImpl>()
    private val adapter = TypeOfBodyAdapter()
    private var listener: ((String) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_type_loading, container, false)

    private val bind by viewBinding(BottomDialogTypeLoadingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.continueSignUpRequest()
        adapter.setListener {
            listener?.invoke(it.name)
        }
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
            adapter.notifyDataSetChanged()
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
    }
    fun setListener(f:(String)->Unit){
        listener=f
    }

}