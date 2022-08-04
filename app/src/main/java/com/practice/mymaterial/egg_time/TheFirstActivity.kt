package com.practice.mymaterial.egg_time

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.mymaterial.stock_created.MainActivity
import com.practice.mymaterial.R
import com.practice.mymaterial.databinding.ActivityTheFirstBinding

class TheFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTheFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTheFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextFirst.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }
    }
}