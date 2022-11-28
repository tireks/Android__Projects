package com.tirex_projs.Calc_new
import com.tirex_projs.Calc_new.databinding.ActivityMainBinding

class Cheker {
    private var testableString : String = "";
    var errorCode : Int = 0;
    var bracketCounter : Int = 0;
    var bracketsPositions : IntArray = IntArray(2)
    private lateinit var binding: ActivityMainBinding;

    fun setTestableStr(String: String){
        testableString = String
    }
    fun fullCheck() : Int {
        val notEnoughBrackets : Boolean = notEnoughBrackets()
        val unnecessaryBrackets: Boolean = unnecessaryBrackets()
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

    private fun notEnoughBrackets() : Boolean{
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

    private fun unnecessaryBrackets() : Boolean {
        var bracketCheck: Boolean = false;
        var errorCheck: Boolean = false;
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
                        errorCheck = true;
                    }
                }
            }
        }
        return errorCheck
    }

    fun fixInputString() : String {
        when (errorCode) {
            1 -> {
                if (bracketCounter > 0){
                    while (bracketCounter > 0){
                        testableString = testableString.plus(")")
                        //binding.TVInput.append(")")
                        bracketCounter--
                    }
                }
                //return testableString
            }
            2 -> {
                var errorCheck: Boolean;
                var bracketCheck: Boolean;
                var stringBuilder: StringBuilder
                do {
                    errorCheck = false;
                    bracketCheck = false;
                    stringBuilder = StringBuilder(testableString)
                    for (char in testableString.indices){
                        when (testableString[char].toString()){
                            "(" -> {
                                if (!bracketCheck){
                                    bracketCheck = true;
                                }
                                if(!errorCheck){
                                    bracketsPositions[0] = char
                                }
                            }
                            "-", "+", "x", "/" -> {
                                bracketCheck = false;
                            }
                            ")" -> {
                                if(!errorCheck){
                                    bracketsPositions[1] = char
                                }
                                if (bracketCheck){
                                    errorCheck = true;

                                }
                            }
                        }
                    }
                    if (errorCheck) {
                        stringBuilder = stringBuilder.deleteAt(bracketsPositions[0])
                        stringBuilder = stringBuilder.deleteAt(bracketsPositions[1] - 1)
                        testableString = stringBuilder.toString()
                    }
                } while (errorCheck)
            }
        }
        return testableString
    }
}