package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.android_netox_miniprojet.R

class StartQuizActivity :AppCompatActivity() {
    lateinit var start:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startquiz)
        supportActionBar?.hide()

        start=findViewById(R.id.start)
        start.setOnClickListener{
            var intent = Intent(this,MainQuizActivity::class.java)
            startActivity(intent)
        }
    }

   /* override fun onBackPressed(){
        super.onBackPressed()
    }*/
    override fun onBackPressed() {
        var intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}