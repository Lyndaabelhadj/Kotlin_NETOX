package tn.esprit.android_netox_miniprojet.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.android_netox_miniprojet.models.Rdv
import tn.esprit.android_netox_miniprojet.retrofit.ApiClient
import tn.esprit.android_netox_miniprojet.services.RdvService


class AddRdvViewModel : ViewModel(){


    var AddRdvLiveData: MutableLiveData<Rdv> = MutableLiveData()
    val _AddRdvLiveData : LiveData<Rdv> =AddRdvLiveData

/*
    fun getRdvById(id:String){
        val retrofit= ApiClient.getApiClient()!!.create(RdvService::class.java)
        val affichRdv=retrofit.getRdvById(id)
        affichRdv.enqueue(object : Callback<Rdv> {
            override fun onResponse(call: Call<Rdv>, response: Response<Rdv>) {
                if (response.isSuccessful){
                    getRdvByIdLiveData.postValue(response.body())
                    Log.i("body", response.body().toString())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    getRdvByIdLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Rdv>, t: Throwable) {
                getRdvByIdLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }

 */




    fun AddRdv(rdv: Rdv){
        val retrofit= ApiClient.getApiClient()!!.create(RdvService::class.java)
        val addRdv=retrofit.AddRdv(rdv)
        addRdv.enqueue(object : Callback<Rdv> {

            override fun onResponse(call: Call<Rdv>, response: Response<Rdv>) {
                if (response.isSuccessful){
                    AddRdvLiveData.postValue(response.body())
                    Log.i("message",  response.body()!!.date.toString())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    AddRdvLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Rdv>, t: Throwable) {
                AddRdvLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }


}