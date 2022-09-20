package com.example.testtaskshift

class ValidationContainer {
    var validStatusName : Boolean = true;
    var validStatusSurname : Boolean = true;
    var validStatusLastname : Boolean = true;
    //var validStatusBirthdate : Boolean = false;
    var validStatusPassInp : Boolean = true;
    var validStatusPassCheck : Boolean = true;

    fun validsUpdate(validType : String, validStatusNew: Boolean){
        when (validType){
            "Name" ->{
                validStatusName = validStatusNew;
            }
            "Surname" ->{
                validStatusSurname = validStatusNew;
            }
            "Lastname" ->{
                validStatusLastname = validStatusNew;
            }
            //"Birthdate" ->{
                //validStatusBirthdate = validStatusNew;
            //}
            "PassInp" ->{
                validStatusPassInp = validStatusNew;
            }
            "PassCheck" ->{
                validStatusPassCheck = validStatusNew;
            }
        }
    }

    fun accessController() : Boolean{
        return validStatusName &&
                validStatusSurname &&
                validStatusLastname &&
                //validStatusBirthdate &&
                validStatusPassInp  &&
                validStatusPassCheck
    }

    fun debugFun() {
    }
}