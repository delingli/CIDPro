package com.example.cidpro

object UrlUtil {

    var SCHEME = "http://106.52.91.56:8080/api/"
    var SERVER = SCHEME + "Home/"
    var GETUSERLISTS = SERVER + "GetUserLists"
    var DeleteUser = SERVER + "DeleteUser"
    var EDITUSERPASSWORD = SERVER + "EditUserPassword"
    var adduser = SERVER + "AddUser"

    //106.52.91.56:8080/api/SysDictionary?typeCode=bank
    var SysDictionary = SCHEME + "SysDictionary"
}