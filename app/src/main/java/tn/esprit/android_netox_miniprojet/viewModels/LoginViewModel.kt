package tn.esprit.android_netox_miniprojet.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import tn.esprit.android_netox_miniprojet.models.loginResponse
import tn.esprit.android_netox_miniprojet.retrofit.ApiClient
import tn.esprit.android_netox_miniprojet.services.UserService

class LoginViewModel : ViewModel(){
    var loginLiveData: MutableLiveData<loginResponse> = MutableLiveData()
    val _loginLiveData: LiveData<loginResponse> = loginLiveData


    fun login(username:String,password:String){
        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val addUser=retrofit.login(username,password)
        addUser.enqueue(object : Callback<loginResponse> {
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                if (response.isSuccessful){
                    Log.i("username",  response.body()!!.user!!.username.toString())
                    loginLiveData.postValue(response.body())
                }else{
                    Log.i("test",  response.body()!!.toString())

                    loginLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                loginLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })

    }

}