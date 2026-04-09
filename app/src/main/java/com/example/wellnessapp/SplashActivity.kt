package com.example.wellnessapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.BounceInterpolator
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtWelcome = findViewById<TextView>(R.id.text)

        // Starting position of the text
        txtWelcome.alpha = 0f
        txtWelcome.translationY = -300f
        txtWelcome.scaleX = 0.3f
        txtWelcome.scaleY = 0.3f
        txtWelcome.rotation = -15f

        // Fade in
        val fade = ObjectAnimator.ofFloat(txtWelcome, "alpha", 0f, 1f)
        fade.duration = 1500

        // Move down from the top
        val move = ObjectAnimator.ofFloat(txtWelcome, "translationY", -300f, 0f)
        move.duration = 2000
        move.interpolator = BounceInterpolator()

        // Zoom in
        val scaleX = ObjectAnimator.ofFloat(txtWelcome, "scaleX", 0.3f, 1.2f, 1f)
        scaleX.duration = 2000

        val scaleY = ObjectAnimator.ofFloat(txtWelcome, "scaleY", 0.3f, 1.2f, 1f)
        scaleY.duration = 2000

        // Slight spin
        val rotate = ObjectAnimator.ofFloat(txtWelcome, "rotation", -15f, 10f, 0f)
        rotate.duration = 2000

        // Play all animations together
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fade, move, scaleX, scaleY, rotate)
        animatorSet.start()

        // Go to MainActivity after 5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }
}

