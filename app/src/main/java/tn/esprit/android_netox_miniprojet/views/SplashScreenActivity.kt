package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import tn.esprit.android_netox_miniprojet.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)

        Handler(mainLooper).postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(applicationContext,IntroActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}