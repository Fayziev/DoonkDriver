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
import uz.anorgroup.doonkdriver.databinding.BottomDialogAutoTypeBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AvtoTypeAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.AvtoTypeDialogViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.AvtoTypeDialogViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class AutoTypeBottomDialog : BottomSheetDialogFragment() {
    private val viewModel: AvtoTypeDialogViewModel by viewModels<AvtoTypeDialogViewModelImpl>()
    private val adapter = AvtoTypeAdapter()
    private var listener: ((String) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_auto_type, container, false)

    private val bind by viewBinding(BottomDialogAutoTypeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.autoTypeRequest()
        adapter.setListener {
            listener?.invoke(it.name)
        }
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }

}