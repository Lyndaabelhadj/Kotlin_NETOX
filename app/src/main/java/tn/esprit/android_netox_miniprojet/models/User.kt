package tn.esprit.android_netox_miniprojet.models

import java.util.*

    data class User (
        var _id: String,
        var username: String? =null,
        var email: String? =null,
        var role: String,
        //var datedenaissance: Date? =null,
        var password: String? =null,
        var image: String,
        var createdAt: String? =null,
        var updatedAt: String? =null,
        var __v:Int
    )
data class ResponseUser (
    var _id: String,
    var username: String? =null,
    var email: String? =null,
    var role: String,
    var password: String? =null,
    //var datedenaissance: Date? =null,
    //var image: String,
    var __v:Int
)

    data class loginResponse (var message: String? = null, var accessToken: String, var user:User?=null)
