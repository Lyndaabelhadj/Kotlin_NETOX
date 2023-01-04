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

class UpdateProfileViewModel : ViewModel() {


    var getUserByIdLiveData: MutableLiveData<User> = MutableLiveData()
    val _getUserByIdLiveData : LiveData<User> =getUserByIdLiveData


    var UpdateprofileLiveData: MutableLiveData<User> = MutableLiveData()
    val _UpdateprofileLiveData : LiveData<User> =UpdateprofileLiveData


    fun getUserById(id:String){
        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val modifUser=retrofit.getUserById(id)
        modifUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    getUserByIdLiveData.postValue(response.body())
                    Log.i("body", response.body().toString())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    getUserByIdLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                getUserByIdLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }





     fun UpdateProfile(idUser: String, username: RequestBody, email: RequestBody) {
        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val UpdateUser=retrofit.UpdateProfile(idUser,username,email )
        UpdateUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    UpdateprofileLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    UpdateprofileLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                UpdateprofileLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }


}