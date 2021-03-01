package co.ravn.kevin.masterdetail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import co.ravn.kevin.masterdetail.R
import co.ravn.kevin.masterdetail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val navController get() = navHostFragment.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.posts_fragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}