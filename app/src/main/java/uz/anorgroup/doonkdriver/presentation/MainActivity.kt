package uz.anorgroup.doonkdriver.presentation

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.reciver.InternetBroadCast
import uz.anorgroup.doonkdriver.presentation.viewmodel.StartScreenViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.StartScreenViewModelImpl

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)

//        this.registerReceiver(receiver, IntentFilter())
//        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
//        val navController = navHost.navController.navInflater.inflate(R.navigation.nav_graph)
//        viewModel.startScreenFlow.onEach {
//            if (it) navController.startDestination = R.id.mainScreen
//            else navController.startDestination = R.id.loginScreen
//        }.launchIn(lifecycleScope)
//
//        receiver.setListener {
//            if (it) navController.startDestination = R.id.mainScreen
//            else navController.startDestination = R.id.internetConnectionScreen
//            navHost.navController.graph = navController
//        }
