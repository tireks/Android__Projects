package com.tirex_projs.Calc_new

class Cheker {
    var testableString : String = "";
    var errorCode : Int = 0;
    fun fullCheck(testableString : String) : Int {
        val notEnoughBrackets : Boolean = notEnoughBrackets(testableString)
        val unnecessaryBrackets: Boolean = unnecessaryBrackets(testableString)
        if (notEnoughBrackets || unnecessaryBrackets){
            if (notEnoughBrackets){
                errorCode = 1;
            }
            if (unnecessaryBrackets){
                errorCode = 2;
            }
        }else{
            errorCode = 0
        }
        return  errorCode
    }

    private fun notEnoughBrackets(testableString : String) : Boolean{
        var bracketCounter : Int = 0;
        for (char in testableString.toCharArray()){
            if (char.toString() == "("){
                bracketCounter++
            }
            if (char.toString() == ")"){
                bracketCounter--
            }
        }
        return bracketCounter != 0
    }

    private fun unnecessaryBrackets(testableString : String) : Boolean {
        var bracketCheck: Boolean = false;
        var errorCode: Boolean = false;
        for (char in testableString.indices){
            when (testableString[char].toString()){
                "(" -> {
                    if (!bracketCheck){
                        bracketCheck = true;
                    }
                }
                "-", "+", "x", "/" -> {
                    bracketCheck = false;
                }
                ")" -> {
                    if (bracketCheck){
                        errorCode = true;
                    }
                }
            }
        }
        return errorCode
    }
}