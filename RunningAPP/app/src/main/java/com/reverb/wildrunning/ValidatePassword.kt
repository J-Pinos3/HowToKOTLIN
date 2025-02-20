package com.reverb.wildrunning

import  java.util.regex.Matcher
import  java.util.regex.Pattern


class ValidatePassword {

    companion object{
        var pat: Pattern? = null
        var mat: Matcher? = null

        fun areSamePasswords(originalPassword:String, confirmedPassword: String): Boolean{
            return confirmedPassword == originalPassword
        }
    }

}