package com.example.testtaskshift

class ValidationContainer {
    var validStatusName : Boolean = false;
    var validStatusSurname : Boolean = false;
    var validStatusLastname : Boolean = false;
    var validStatusBirthdate : Boolean = false;
    var validStatusPassInp : Boolean = false;
    var validStatusPassCheck : Boolean = false;

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
            "PassInp" ->{
                validStatusPassInp = validStatusNew;
            }
            "PassCheck" ->{
                validStatusPassCheck = validStatusNew;
            }
            "Birthdate" ->{
                validStatusBirthdate = validStatusNew;
            }
        }
    }

    fun accessController() : Boolean{
        return validStatusName &&
                validStatusSurname &&
                validStatusLastname &&
                validStatusBirthdate &&
                validStatusPassInp  &&
                validStatusPassCheck
    }

}