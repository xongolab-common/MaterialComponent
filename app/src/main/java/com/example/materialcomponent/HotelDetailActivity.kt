package com.example.materialcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.materialcomponent.databinding.ActivityHotelDetailBinding
import com.example.materialcomponent.databinding.ActivityMainBinding

class HotelDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotelDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHotelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreenMode(window)
    }

    private fun setFullScreenMode(window: Window) {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
    }
}