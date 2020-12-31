package com.example.timer

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.timer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val timer = binding.time
        var timeWhenStopped: Long = 0
        binding.startBtn.setOnClickListener{
                timer.base = (SystemClock.elapsedRealtime() + timeWhenStopped)
                timer.start()
               val rotate = AnimationUtils.loadAnimation(this, R.anim.rotation)
                binding.needle.startAnimation(rotate)
                binding.startBtn.visibility = View.INVISIBLE
                binding.pauseBtn.visibility = View.VISIBLE

            }
        binding.pauseBtn.setOnClickListener {
            timeWhenStopped = timer.base - SystemClock.elapsedRealtime()
            timer.stop()
            AnimationUtils.loadAnimation(this, R.anim.rotation)
            binding.needle.animation = null
            binding.pauseBtn.visibility = View.INVISIBLE
            binding.startBtn.visibility = View.VISIBLE

        }
        binding.resetBtn.setOnClickListener {
                timeWhenStopped = 0
                timer.base = SystemClock.elapsedRealtime()
                timer.stop()
                AnimationUtils.loadAnimation(this, R.anim.rotation)
                binding.needle.animation = null
                binding.pauseBtn.visibility = View.INVISIBLE
                binding.startBtn.visibility = View.VISIBLE
            }
    }
}