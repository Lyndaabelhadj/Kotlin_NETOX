package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.android_netox_miniprojet.R

class DoctorsActivity : AppCompatActivity() {


    lateinit var button: Button
    lateinit var button2: Button
    lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_doctors)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(applicationContext, addRdv::class.java)
            startActivity(intent)
            finish()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(applicationContext, addRdv::class.java)
            startActivity(intent)
            finish()
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(applicationContext, addRdv::class.java)
            startActivity(intent)
            finish()
        }




    }}