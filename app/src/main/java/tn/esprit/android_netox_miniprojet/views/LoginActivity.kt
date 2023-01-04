package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.android_netox_miniprojet.MainActivity
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.loginResponse
import tn.esprit.android_netox_miniprojet.viewModels.LoginViewModel


const val ID="id"
const val PREF_LOGIN ="Remember Me"
const val  USERNAME="Username"
const val PASSWORD="Password"
const val TOKEN="Token"
const val EMAIL="Email"

class LoginActivity : AppCompatActivity() {
    private  lateinit var txtLayoutUsername: TextInputLayout
    private  lateinit var txtLayoutPassword: TextInputLayout

    lateinit var editTextPersonName: EditText
    lateinit var editTextPassword: EditText

    lateinit var tv_inscription: TextView
    lateinit var btnInscription: Button
    lateinit var viewModel: LoginViewModel

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        txtLayoutUsername=findViewById(R.id.txt_layout_username)
        txtLayoutPassword=findViewById(R.id.txt_layout_password)

        editTextPersonName=findViewById(R.id.editTextPersonName)
        editTextPassword=findViewById(R.id.editTextPassword)

        prefs = getSharedPreferences(PREF_LOGIN,MODE_PRIVATE)


        if (prefs.getString(USERNAME,"")!!.isNotEmpty() and prefs.getString(PASSWORD,"")!!.isNotEmpty()){
            val intent = Intent(applicationContext,HomeActivity::class.java)
            startActivity(intent)
        }


        btnInscription=findViewById(R.id.btn_login)
        //txtInscription=findViewById(R.id.txt_inscription)
        //txtInscription.setOnClickListener {
          //  val intent = Intent(applicationContext, SignUpActivity::class.java)
           // startActivity(intent)
            //finish()
       // }

        btnInscription.setOnClickListener {
            if (validationUsername()&&validationPassword()){
                login()
            }
        }

        tv_inscription=findViewById(R.id.tv_inscription)
        tv_inscription.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //validationUsername
    fun validationUsername(): Boolean {
        val username = editTextPersonName.text.toString().trim()

        if (username.isEmpty()) {

            txtLayoutUsername.setError("Required Field")
            return false

        }else {
            txtLayoutUsername.setError(null)
            return true
        }



        return false
    }


    //validation mot de passe
    fun validationPassword(): Boolean {
        val password = editTextPassword.text.toString().trim ()
        if(password.isEmpty()) {
            txtLayoutPassword.setError("Required Field")
            return false
        } else {
            txtLayoutPassword.setError(null)
            return true
        }}


    private fun login() {
        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.login(editTextPersonName.text.toString().trim(),editTextPassword.text.toString().trim())
        viewModel._loginLiveData.observe(this, Observer<loginResponse>{

            if (it!=null){

                //Edit the SharedPreferences by putting all the data
                prefs.edit().apply(){
                    putString(ID, it.user?._id)
                    putString(USERNAME, it.user?.username)
                    putString(PASSWORD, it.user?.password)
                    putString(TOKEN,it.accessToken)
                    putString(EMAIL,it.user?.email)
                    apply()
                }

                //val i = Intent(applicationContext, HomeActivity::class.java)
                //startActivity(i)
                Toast.makeText(applicationContext, "Login success !"+it.accessToken, Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Login failed !", Toast.LENGTH_LONG).show()
            }
        })
    }
}


