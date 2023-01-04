package tn.esprit.android_netox_miniprojet.views

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.User
import tn.esprit.android_netox_miniprojet.viewModels.SignUpViewModel
import java.io.File
import java.io.FileOutputStream


class SignUpActivity : AppCompatActivity() {
    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
    private val STORAGE_PERMISSION_CODE = 1
    private lateinit var imgUser: ImageView
    private lateinit var imgUri: Uri


    lateinit var edtUsername: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtRole: EditText
    lateinit var btnInscription: Button
    lateinit var btnCancel: Button

    lateinit var viewModel: SignUpViewModel
    lateinit var txtLogin: TextView

    private lateinit var txtLayoutEmail: TextInputLayout
    private lateinit var txtLayoutUsername: TextInputLayout
    private lateinit var txtLayoutPassword: TextInputLayout

    //private  lateinit var txtLayoutDateNaissance: TextInputLayout
    private lateinit var txtLayoutRole: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        txtLayoutUsername = findViewById(R.id.txtLayoutUsername)
        txtLayoutEmail = findViewById(R.id.txtLayoutEmail)
        txtLayoutRole = findViewById(R.id.txtLayoutRole)
        //  txtLayoutDateNaissance=findViewById(R.id.editTextDate)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)

        imgUser = findViewById(R.id.img_user)

        edtUsername = findViewById(R.id.editTextPersonName)
        edtEmail = findViewById(R.id.editTextEmail)
        edtPassword = findViewById(R.id.editTextPassword)
        edtRole = findViewById(R.id.editTextRole)

        btnInscription = findViewById(R.id.btn_signup)
        btnCancel = findViewById(R.id.btn_cancel)
        txtLogin = findViewById(R.id.txt_login)


        //val toolbar =findViewById<MaterialToolbar>(R.id.material_toolbar)
        //setSupportActionBar(toolbar)
        //getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        //toolbar.setTitle("Inscription")
        // toolbar.setNavigationIcon(R.drawable.ic_back)
        // toolbar.setNavigationOnClickListener { onBackPressed() }

        txtLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


       // imgUser.setOnClickListener {
        //     if (ContextCompat.checkSelfPermission(
        //        applicationContext,
        //        Manifest.permission.READ_EXTERNAL_STORAGE
        //   ) == PackageManager.PERMISSION_GRANTED
        //  ) {
            //     openGallery()
            //  } else {
            //       requestStoragePermission();
            //   }

        //  }
        btnInscription.setOnClickListener {
            if (validationUsername() && validationEmail() && validationPassword()) {
                signUp()
            }
        }

        btnCancel.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun signUp() {
        // val fileDir = applicationContext.filesDir
        //  val file = File(fileDir, "image.jpg")
        //val inputStream = contentResolver.openInputStream(imgUri)
        //val outputStream = FileOutputStream(file)
        //inputStream!!.copyTo(outputStream)
        //val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        //val image = MultipartBody.Part.createFormData("image", file.name, requestBody)


        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        val username = edtUsername.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val role = edtRole.text.toString().trim()
        val password = edtPassword.text.toString().trim()
   val user = User("",username,email,role,password,"","","",0)

        viewModel.signUp(user)
        viewModel._signUpLiveData.observe(this, Observer<User> {
            if (it != null) {
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
                Toast.makeText(applicationContext, "welcome", Toast.LENGTH_LONG).show()
//text:file.name
            } else {
                Toast.makeText(applicationContext, "failure", Toast.LENGTH_LONG).show()
            }
        })
    }


    fun validationUsername(): Boolean {
        val username = edtUsername.text.toString().trim()
        if (username.isEmpty()) {

            txtLayoutUsername.setError("Champ obligatoire")
            return false
        } else {
            txtLayoutUsername.setError(null)
            return true
        }
    }


    private fun validationEmail(): Boolean {
        val email = edtEmail.text.toString().trim()
        if (email.isEmpty()) {
            txtLayoutEmail.setError("Champ obligatoire")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtLayoutEmail.setError("Verifier votre e-mail")
            return false
        } else {
            txtLayoutEmail.setError(null)
            return true
        }
    }

    private fun validationPassword(): Boolean {
        val password = edtPassword.text.toString().trim()
        if (password.isEmpty()) {
            txtLayoutPassword.setError("Champ obligatoire")
            return false
        } else {
            txtLayoutPassword.setError(null)
            return true
        }
    }


   // private fun openGallery() {
     //   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).apply {
       //     type = "image/*"
         //   startActivityForResult(this, IMAGE_GALLERY_REQUEST_CODE)
        //}
   // }

   // override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //    super.onActivityResult(requestCode, resultCode, data)
      //  if (resultCode == RESULT_OK && requestCode == IMAGE_GALLERY_REQUEST_CODE) {
        //    if (data != null && data.data != null) {
          //      if (Build.VERSION.SDK_INT >= 28) {
            //        imgUri = data.data!!

              //      imgUser.setImageURI(imgUri)

                //}
            //}
        //}
    //}




    //private fun requestStoragePermission() {
    //  if (ActivityCompat.shouldShowRequestPermissionRationale(
    //  this,
    //   Manifest.permission.READ_EXTERNAL_STORAGE
    // )
    //  ) {
    //    AlertDialog.Builder(this)
    //     .setTitle("Autorisation nécessaire")
    //    .setMessage("Cette autorisation est nécessaire pour ajouter une image")
    //    .setPositiveButton("D'accord",
    //     DialogInterface.OnClickListener { dialog, which ->
    //       ActivityCompat.requestPermissions(
    //          this@SignUpActivity,
    //        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
    //        STORAGE_PERMISSION_CODE
    //        )
    //      })
    //   .setNegativeButton("Annuler",
    //      DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
    //   .create().show()
    // } else {
    //   ActivityCompat.requestPermissions(
    //      this,
    //      arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
    //      STORAGE_PERMISSION_CODE
    //  )
    //  }
    //}
    //override fun onRequestPermissionsResult(
    //    requestCode: Int,
    //   permissions: Array<String?>,
    //   grantResults: IntArray
    //) {
    //    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    //   if (requestCode == STORAGE_PERMISSION_CODE) {
    //     if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    //       openGallery()
    //      Toast.makeText(this, "Permission accordée", Toast.LENGTH_SHORT).show()
    //  } else {
    //       Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show()
    //   }
    //  }
    // }
}