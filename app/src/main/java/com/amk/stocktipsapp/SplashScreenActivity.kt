package com.amk.stocktipsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import com.amk.stocktipsapp.databinding.ActivitySplashScreenBinding
import com.amk.stocktipsapp.ui.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }

        val sceneRoot: ViewGroup = binding.splashSceneRoot
        val anotherScene: Scene =
            Scene.getSceneForLayout(sceneRoot, R.layout.splash_scene_second, this)

        val fade1: Animation = AnimationUtils.loadAnimation(this, R.anim.anim_show)
        binding.splashSceneRoot.startAnimation(fade1)
        fade1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                val transitionSet = TransitionSet()
                transitionSet.addTransition(Fade())
                transitionSet.addTransition(ChangeBounds())
                transitionSet.ordering = TransitionSet.ORDERING_TOGETHER
                transitionSet.duration = 2500
                transitionSet.interpolator = AccelerateInterpolator()
                TransitionManager.go(anotherScene, transitionSet)
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_show, R.anim.anim_gone_activity)
        }, 4000)
    }
}