package com.example.timer

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private  lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val timer = binding.time

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.timeWhenStart.observe(this, {
            timer.base = viewModel.samaye + SystemClock.elapsedRealtime()
        })

        viewModel.timeWhenPause.observe(this, {
            viewModel.samaye = timer.base - SystemClock.elapsedRealtime()
        })

        viewModel.timeWhenReset.observe(this, {
            timer.base = SystemClock.elapsedRealtime()
        })

        binding.startBtn.setOnClickListener {
            viewModel.timeWhenStart.value = timer.base
            timer.start()
            val rotate = AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotation)
            binding.needle.startAnimation(rotate)
            binding.startBtn.visibility = View.INVISIBLE
            binding.pauseBtn.visibility = View.VISIBLE

        }
        binding.pauseBtn.setOnClickListener {
            viewModel.timeWhenPause.value = viewModel.timeWhenStart.value
            timer.stop()
            AnimationUtils.loadAnimation(this, R.anim.rotation)
            binding.needle.animation = null
            binding.pauseBtn.visibility = View.INVISIBLE
            binding.startBtn.visibility = View.VISIBLE
        }
        binding.resetBtn.setOnClickListener {
            viewModel.samaye = 0
            viewModel.timeWhenReset.value = viewModel.samaye
            timer.base = SystemClock.elapsedRealtime()
            timer.stop()
            AnimationUtils.loadAnimation(this, R.anim.rotation)
            binding.needle.animation = null
            binding.pauseBtn.visibility = View.INVISIBLE
            binding.startBtn.visibility = View.VISIBLE
        }

        if (savedInstanceState != null) {
            viewModel.samaye = savedInstanceState.getLong("count")
            timer.base = SystemClock.elapsedRealtime() + viewModel.samaye
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("count",viewModel.samaye)
    }




}


