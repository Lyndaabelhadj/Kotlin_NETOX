package tn.esprit.android_netox_miniprojet.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.android_netox_miniprojet.models.User
import tn.esprit.android_netox_miniprojet.retrofit.ApiClient
import tn.esprit.android_netox_miniprojet.services.UserService

class SignUpViewModel : ViewModel() {
    var signUpLiveData: MutableLiveData<User> = MutableLiveData()
    val _signUpLiveData : LiveData<User> = signUpLiveData


    fun signUp(user:User){
        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val addUser=retrofit.signUp(user)
        addUser.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    signUpLiveData.postValue(response.body())
                    Log.i("message",  response.body()!!.username.toString())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    signUpLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                signUpLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }
}