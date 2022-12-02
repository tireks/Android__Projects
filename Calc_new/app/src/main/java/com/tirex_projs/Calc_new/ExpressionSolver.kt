package com.tirex_projs.Calc_new

class ExpressionSolver {
    private var inputString : String = ""

    fun setInputString (input: String) {
        inputString = input
    }
    fun solveExpression(inputExpression: String): Double {
        setInputString(inputExpression)
        var PRNexpression: String = makePRN()
        return calculate(PRNexpression)
    }

    private fun makePRN() : String{
        var  output = " "
        val operStack: ArrayDeque<String>
        
    }

    private fun calculate(input: String) : Double{

    }

    private fun isSplitter(inputChar: String) : Boolean{
        return (inputChar == " ")
    }

    private fun isOperator(inputChar: String): Boolean {
        return ((inputChar == "+") or
                (inputChar == "-") or
                (inputChar == "x") or
                (inputChar == "/") or
                (inputChar == "(") or
                (inputChar == ")"))
    }

    private fun getPriority(inputChar: String) : Int{
        when (inputChar){
            "(" -> {
                return 0
            }
            ")" -> {
                return 1
            }
            "+" -> {
                return 2
            }
            "-" -> {
                return 3
            }
            "x" -> {
                return 4
            }
            "/" -> {
                return 5
            }
            else -> {
                return 6
            }
        }
    }
}