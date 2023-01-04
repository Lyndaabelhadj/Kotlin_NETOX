package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import tn.esprit.android_netox_miniprojet.MainActivity
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.loginResponse

class IntroActivity : AppCompatActivity() {

    lateinit var btngetstarted: Button
    lateinit var btnskip: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btngetstarted=findViewById(R.id.btn_getstarted)
        btngetstarted.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }


        btnskip=findViewById(R.id.btn_skip)
        btnskip.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }



}}