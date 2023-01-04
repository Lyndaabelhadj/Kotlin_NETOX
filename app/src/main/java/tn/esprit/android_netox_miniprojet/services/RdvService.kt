package tn.esprit.android_netox_miniprojet.services

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.android_netox_miniprojet.models.Rdv


interface RdvService {

    @GET("rdv/{idRdv}")
    fun getRdvById(
        @Path("idRdv") idRdv: String
    ): Call<Rdv>


    @POST("rdv")
    fun AddRdv(
        @Body rdv: Rdv ,
    ): Call<Rdv>


    @Multipart
    @PUT("rdv/{idRdv}")
    fun UpdateRdv(
        @Path("idRdv") idRdv: String,
        @Part("date") date: RequestBody,
        @Part("heure") heure: RequestBody,
    ): Call<Rdv>

}