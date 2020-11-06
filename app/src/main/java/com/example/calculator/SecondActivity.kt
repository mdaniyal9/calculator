package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private var numOne:Float = 0.0F
    private var numTwo:Float = 0.0F
    private var currentOperator:MathOperator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the keyboard.
        text_edit.setShowSoftInputOnFocus(false)



        btn_zero.setOnClickListener(this)
        btn_one.setOnClickListener(this)
        btn_two.setOnClickListener(this)
        btn_three.setOnClickListener(this)
        btn_four.setOnClickListener(this)
        btn_five.setOnClickListener(this)
        btn_six.setOnClickListener(this)
        btn_seven.setOnClickListener(this)
        btn_eight.setOnClickListener(this)
        btn_nine.setOnClickListener(this)
        btn_equal.setOnClickListener(this)
        btn_divide.setOnClickListener(this)
        btn_multiply.setOnClickListener(this)
        btn_minus.setOnClickListener(this)
        btn_plus.setOnClickListener(this)
        btn_clear.setOnClickListener(this)
        btn_bracket.setOnClickListener(this)
        btn_dot.setOnClickListener(this)
        btn_back.setOnClickListener(this)
        btn_mod.setOnClickListener(this)

//        text_edit.doAfterTextChanged {
//            if(text_edit.text.startsWith("0"))
//                text_edit.text.clear()
//        }

    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_zero -> {
                text_edit.text.append("0")
            }
            R.id.btn_one -> {
                text_edit.text.append("1")
            }
            R.id.btn_two -> {
                text_edit.text.append("2")
            }
            R.id.btn_three -> {
                text_edit.text.append("3")
            }
            R.id.btn_four -> {
                text_edit.text.append("4")
            }
            R.id.btn_five -> {
                text_edit.text.append("5")
            }
            R.id.btn_six -> {
                text_edit.text.append("6")
            }
            R.id.btn_seven -> {
                text_edit.text.append("7")
            }
            R.id.btn_eight -> {
                text_edit.text.append("8")
            }
            R.id.btn_nine -> {
                text_edit.text.append("9")
            }
            R.id.btn_equal -> {

//                val regex = ("^(\\d+[\\+\\-\\*\\/]{1})+\\d+\$").toRegex()
                val regex = ("^([+-]?(\\d*\\.)?\\d+[\\+\\-\\*\\/\\%]{1})+[+-]?(\\d*\\.)?\\d+\$").toRegex()
                val myString = text_edit.text.toString()
//                if (!text_edit.text.toString().equals("")) {
                if(myString.matches(regex)){

                    val operatorList: MutableList<String> = ArrayList()
                    val operandList: MutableList<String> = ArrayList()
                    val st = StringTokenizer(myString, "+-*/%", true)
                    while (st.hasMoreTokens()) {
                        val token = st.nextToken()
                        if ("+-/*%".contains(token)) {
                            operatorList.add(token)
                        } else {
                            operandList.add(token)
                        }
                    }


                    var result: Float = 0.0F

                    for (i in 0..operatorList.size-1)
                    {
                        if(i == 0)
                            result = calculate(operandList.get(i).toFloat(), operandList.get(i+1).toFloat(), operatorList.get(i))
                        else
                            result = calculate(result, operandList.get(i+1).toFloat(), operatorList.get(i))
                    }

                    text_edit.text.clear()
                    text_edit.text.append(result.toString())

                    Log.e("CALCULATOR", result.toString())
                }
                else
                {
                    Toast.makeText(this,"Please enter another number or remove operator from end", Toast.LENGTH_LONG).show()
                }
            }
            R.id.btn_divide -> {
                if(!text_edit.text.endsWith("/")) {
                    text_edit.text.append("/")
                    doMathOperation(MathOperator.DIVIDE)
                }
            }
            R.id.btn_multiply -> {
                text_edit.text.append("*")
                doMathOperation(MathOperator.MULTIPLY)
            }
            R.id.btn_minus -> {
                text_edit.text.append("-")
                doMathOperation(MathOperator.MINUS)
            }
            R.id.btn_plus -> {
                text_edit.text.append("+")
                doMathOperation(MathOperator.PLUS)
            }
            R.id.btn_clear -> {
                text_edit.text.clear()
                numOne = 0.0F
                numTwo = 0.0F
                currentOperator = null
            }
            R.id.btn_bracket -> {
                //apply a regex here for brackets
                //text_edit.text.append("()")
            }
            R.id.btn_dot -> {
                text_edit.text.append(".")
            }
            R.id.btn_back -> {
                if(!text_edit.text.toString().equals("")) {
                    val newText = text_edit.text.substring(0, text_edit.text.toString().length - 1)
                    text_edit.text.clear()
                    text_edit.text.append(newText)
                }
                else
                {
                    Toast.makeText(this,"Already empty", Toast.LENGTH_LONG).show()
                }

            }
            R.id.btn_mod -> {
                text_edit.text.append("%")
                doMathOperation(MathOperator.MOD)
            }
        }
    }

    fun doMathOperation(operator: MathOperator)
    {
        if(!text_edit.text.toString().equals("")) {
            currentOperator = operator
        }
    }

    fun calculate(numberOne:Float, numberTwo:Float, operation:String): Float {
        when(operation){
            "/" -> {
                return (numberOne/numberTwo)
            }
            "*" -> {
                return (numberOne*numberTwo)
            }
            "-" -> {
                return (numberOne-numberTwo)
            }
            "+" -> {
                return (numberOne+numberTwo)
            }
            "%" -> {
                return (numberOne%numberTwo)
            }

            else -> return 0.0F
        }
    }
}