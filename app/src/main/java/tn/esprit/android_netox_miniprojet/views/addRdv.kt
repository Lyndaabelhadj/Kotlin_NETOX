package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_rdv.*
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.Rdv
import tn.esprit.android_netox_miniprojet.viewModels.AddRdvViewModel


class addRdv : AppCompatActivity() {

    lateinit var viewModel: AddRdvViewModel
    lateinit var btn_addRdv: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rdv)

        btn_addRdv = findViewById(R.id.btn_addRdv)


        btn_addRdv.setOnClickListener {
            /*val intent = Intent(applicationContext, DoctorsActivity::class.java)
            startActivity(intent)
            finish()*/
            AddRdv()
        }


    }

    fun AddRdv() {

        viewModel = ViewModelProvider(this).get(AddRdvViewModel::class.java)

        val date = editTextDate.text.toString().trim()
        val heure = editTextHeure.text.toString().trim()

        val rdv = Rdv("", date, heure, 0)

        viewModel.AddRdv(rdv)
        viewModel._AddRdvLiveData.observe(this, Observer<Rdv> {
            if (it != null) {
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
                Toast.makeText(applicationContext, "Add with success", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(applicationContext, "failure", Toast.LENGTH_LONG).show()
            }
        })
    }




}