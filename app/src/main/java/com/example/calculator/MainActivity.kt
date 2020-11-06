package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        text_edit.doAfterTextChanged {
            if(text_edit.text.startsWith("0"))
                text_edit.text.clear()
        }

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
                if(!text_edit.text.toString().equals("") && currentOperator != null) {
                    numTwo = text_edit.text.toString().toFloat()
                    text_edit.text.clear()
                    when (currentOperator) {
                        MathOperator.DIVIDE -> {
                            if(numTwo!=0.0F) {
                                text_edit.text.append((numOne / numTwo).toString())
                                numOne = numOne / numTwo
                            }
                            else
                                Toast.makeText(this,"Please enter a number greater than Zero", Toast.LENGTH_LONG).show()
                        }
                        MathOperator.MULTIPLY -> {
                            text_edit.text.append((numOne * numTwo).toString())
                            numOne = numOne * numTwo
                        }
                        MathOperator.MINUS -> {
                            text_edit.text.append((numOne - numTwo).toString())
                            numOne = numOne - numTwo
                        }
                        MathOperator.PLUS -> {
                            text_edit.text.append((numOne + numTwo).toString())
                            numOne = numOne + numTwo
                        }
                    }
                }
            }
            R.id.btn_divide -> {
                doMathOperation(MathOperator.DIVIDE)
            }
            R.id.btn_multiply -> {
                doMathOperation(MathOperator.MULTIPLY)
            }
            R.id.btn_minus -> {
                doMathOperation(MathOperator.MINUS)
            }
            R.id.btn_plus -> {
                doMathOperation(MathOperator.PLUS)
            }
            R.id.btn_clear -> {
                text_edit.text.clear()
                numOne = 0.0F
                numTwo = 0.0F
                currentOperator = null
            }
        }
    }

    fun doMathOperation(operator: MathOperator)
    {
        if(!text_edit.text.toString().equals("")) {
            numOne = text_edit.text.toString().toFloat()
            text_edit.text.clear()
            currentOperator = operator
        }
    }
}