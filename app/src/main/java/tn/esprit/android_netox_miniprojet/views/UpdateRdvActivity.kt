package tn.esprit.android_netox_miniprojet.views

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.Rdv
import tn.esprit.android_netox_miniprojet.viewModels.UpdateRdvViewModel
import java.sql.Time
import java.util.Date

class UpdateRdvActivity: AppCompatActivity() {

    lateinit var editTextDate: Date
    lateinit var button_update_rdv: Button
    lateinit var editTextTime: Time


    lateinit var viewModel: UpdateRdvViewModel

/*

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity_profile)

        editTextDate=findViewById(R.id.editTextDate)
        editTextTime=findViewById(R.id.editTextTime)



        button_update_rdv=findViewById(R.id.button_update_rdv)

        val prefs = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)

        val id = prefs.getString(ID, "")
        Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
        getRdvById(id!!)




        button_update_rdv.setOnClickListener {
            UpdateRdv(id!!)
        }


    }


    private fun getRdvById(id : String) {
        viewModel= ViewModelProvider(this).get(UpdateRdvViewModel::class.java)
        viewModel.getRdvById(id)
        viewModel._getRdvByIdLiveData.observe(this, Observer<Rdv>{

            if (it!=null){
                editTextDate.time(it.date)
                editTextTime.time(it.heure)

                Toast.makeText(applicationContext, "success",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(applicationContext, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun UpdateRdv(idRdv : String) {


        viewModel= ViewModelProvider(this).get(UpdateRdvViewModel::class.java)


        val date=editTextDate.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val heure=editTextTime.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())



        viewModel.UpdateRdv(idRdv,date, heure)

        Toast.makeText(applicationContext,"UPDATED!!", Toast.LENGTH_SHORT).show()
        finish()


    }
*/
}