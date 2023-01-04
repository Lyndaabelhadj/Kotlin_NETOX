package tn.esprit.android_netox_miniprojet.services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.android_netox_miniprojet.models.User
import tn.esprit.android_netox_miniprojet.models.loginResponse

interface UserService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username:String,
              @Field("password") password:String
    ): Call<loginResponse>
//:Observable<String>



    @POST("user")
    fun signUp(
      @Body user : User,
    ): Call<User>



    // @Multipart
    // @FormUrlEncoded
    //@POST("user")
   // fun signUp(
     //   @Body
      //  user:User
        /* @Field("username") username: String,
         @Field("role") role: String,
         @Field("email") email: String,
         @Field("password") password: String,
         @Field("datedenaissance") datedenaissance: String,
        // @Part image: MultipartBody.Part, */
  //  ): Call<User>


    @GET("user/{idUser}")
    fun getUserById(
        @Path("idUser") idUser: String
    ): Call<User>



    @Multipart
    @PATCH("user/{idUser}")
    fun UpdateProfile(
        @Path("idUser") idUser: String,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,

    ): Call<User>


}