package tn.esprit.android_netox_miniprojet.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.android_netox_miniprojet.models.Rdv
import tn.esprit.android_netox_miniprojet.retrofit.ApiClient
import tn.esprit.android_netox_miniprojet.services.RdvService


class UpdateRdvViewModel: ViewModel()  {


    var getRdvByIdLiveData: MutableLiveData<Rdv> = MutableLiveData()
    val _getRdvByIdLiveData : LiveData<Rdv> =getRdvByIdLiveData


    var UpdateRdvLiveData: MutableLiveData<Rdv> = MutableLiveData()
    val _UpdateRdvLiveData : LiveData<Rdv> =UpdateRdvLiveData




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





    fun UpdateRdv(idRdv: String, date: RequestBody, heure: RequestBody) {
        val retrofit= ApiClient.getApiClient()!!.create(RdvService::class.java)
        val UpdateRdv=retrofit.UpdateRdv(idRdv,date,heure )
        UpdateRdv.enqueue(object : Callback<Rdv> {
            override fun onResponse(call: Call<Rdv>, response: Response<Rdv>) {
                if (response.isSuccessful){
                    UpdateRdvLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    UpdateRdvLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Rdv>, t: Throwable) {
                UpdateRdvLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }



}