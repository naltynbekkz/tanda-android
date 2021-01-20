package com.naltynbekkz.tanda.ui

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        }

        viewModel.delayedRefreshTokenLiveData.observe(this) {
            val activity = if (it == null) AuthActivity::class.java else MainActivity::class.java
            startActivity(Intent(this, activity).addFlags(FLAG_ACTIVITY_NO_ANIMATION))
            finish()
            overridePendingTransition(0, 0)
        }

    }

}