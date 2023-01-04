package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import tn.esprit.android_netox_miniprojet.R

class ProfileActivity : AppCompatActivity() {

    lateinit var btn_updateProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        btn_updateProfile= findViewById(R.id.btn_updateProfile)
        btn_updateProfile.setOnClickListener {
            val intent = Intent(applicationContext, UpdateProfileActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}