package com.tirex_projs.Calc_new

class ExpressionSolver {
    private var inputStr : String = ""

    private fun setInputString (input: String) {
        inputStr = input
    }
    private fun unaryMinusController(){
        var stringBuilder = StringBuilder(inputStr)
        for(charInd in inputStr.indices){
            if (charInd == 0){
                if (inputStr[charInd].toString() == "-"){
                    inputStr = "~" + inputStr.substring(1)
                }
            }
            else if ((inputStr[charInd].toString() == "-") &&
                (inputStr[charInd-1].toString() == "(")){
                inputStr = inputStr.substring(0, charInd) + "~" + inputStr.substring(charInd + 1)
            }
        }
    }
    fun solveExpression(inputExpression: String): String { // string- временно, пока тестирую и нет вычислений
        setInputString(inputExpression)
        /*var PRNexpression: String = makePRN()
        return calculate(PRNexpression)*/
        unaryMinusController()
        var string1 = inputStr
        return string1
    }

    /*private fun makePRN() : String{
        var  output = " "
        val operStack: ArrayDeque<String>

    }*/

    /*private fun calculate(input: String) : Double{

    }*/

    /*private fun isSplitter(inputChar: String) : Boolean{
        return (inputChar == " ")
    }*/

    /*private fun isOperator(inputChar: String): Boolean {
        return inputChar.contains("[+x/()-]".toRegex())
    }*/

    /*private fun isDigitOrDot(inputChar: String) : Boolean{
        return inputChar.contains("[1234567890.]".toRegex())
    }

    private fun getPriority(inputChar: String) : Int{
        when (inputChar){
            /*"(" -> {
                return 0
            }
            ")" -> {
                return 1
            }*/
            "+" -> {
                return 1
            }
            "-" -> {
                return 1
            }
            "x" -> {
                return 2
            }
            "/" -> {
                return 2
            }
            else -> {
                return 0
            }
        }
    }*/
}