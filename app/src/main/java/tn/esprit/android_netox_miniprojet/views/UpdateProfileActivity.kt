package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso.get
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.User
import tn.esprit.android_netox_miniprojet.viewModels.UpdateProfileViewModel
import java.io.File
import java.io.FileOutputStream

const val PREF_PROFILE ="Remember Me"

class UpdateProfileActivity : AppCompatActivity() {



    lateinit var editText_update_profile_name: EditText
    lateinit var editText_update_profile_email: EditText
    lateinit var editText_update_profile_password: EditText
    lateinit var img_user: ImageView

    lateinit var viewModel: UpdateProfileViewModel

    lateinit var buttonUpdateProfile: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity_profile)

        editText_update_profile_name=findViewById(R.id.editText_update_profile_name)
        editText_update_profile_email=findViewById(R.id.editText_update_profile_email)
        editText_update_profile_password=findViewById(R.id.editText_update_profile_password)
        img_user=findViewById(R.id.img_user)

        buttonUpdateProfile=findViewById(R.id.button_update_profile)

        val prefs = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)

        val id = prefs.getString(ID, "")
        Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
        getUserById(id!!)




        buttonUpdateProfile.setOnClickListener {
            UpdateProfile(id!!)
        }


    }


        private fun getUserById(id : String) {
            viewModel= ViewModelProvider(this).get(UpdateProfileViewModel::class.java)
            viewModel.getUserById(id)
            viewModel._getUserByIdLiveData.observe(this, Observer<User>{

                if (it!=null){
                    editText_update_profile_name.setText(it.username)
                    editText_update_profile_email.setText(it.email)
                    editText_update_profile_password.setText(it.password)
                    get().load(it.image).into(img_user)
                    Toast.makeText(applicationContext, it.image, Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(applicationContext, "failure", Toast.LENGTH_SHORT).show()
                }
            })
        }



    //prefs = getSharedPreferences(PREF_PROFILE,MODE_PRIVATE)



    private fun UpdateProfile(idUser : String) {


        viewModel= ViewModelProvider(this).get(UpdateProfileViewModel::class.java)


        val username=editText_update_profile_name.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val email=editText_update_profile_email.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())



        viewModel.UpdateProfile(idUser,username, email)

       Toast.makeText(applicationContext,"UPDATED!!",Toast.LENGTH_SHORT).show()
        finish()
    }







}
