package com.practice.mymaterial.egg_time

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.practice.mymaterial.R
import com.practice.mymaterial.base_content.BaseActivity
import com.practice.mymaterial.databinding.ActivityTheFirstBinding

class TheFirstActivity : BaseActivity() {

    private var mNavController: NavController? = null
    private lateinit var binding: ActivityTheFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTheFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.containerFragments) as NavHostFragment
        mNavController = navHostFragment.navController

        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }*/
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.containerFragments) as NavHostFragment
        mNavController = navHostFragment.navController
    }

    override fun getNavController(): NavController? =  mNavController

}