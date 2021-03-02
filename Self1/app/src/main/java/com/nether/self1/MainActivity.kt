package com.nether.self1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.Exception
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var mIterCount : EditText
    private lateinit var mRadioType : RadioGroup
    private lateinit var mCalcPlus : TextView
    private lateinit var mCalcSub : TextView
    private lateinit var mCalcMul : TextView
    private lateinit var mCalcDiv : TextView
    private lateinit var mA : TextView
    private lateinit var mB : TextView
    private lateinit var mCalcButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        mIterCount = findViewById(R.id.editIterAmount)
        mRadioType = findViewById(R.id.radioType)
        mCalcPlus = findViewById(R.id.textCalcPlus)
        mCalcSub = findViewById(R.id.textCalcSub)
        mCalcMul = findViewById(R.id.textCalcMul)
        mCalcDiv = findViewById(R.id.textCalcDiv)
        mA = findViewById(R.id.textA)
        mB = findViewById(R.id.textB)
        mCalcButton = findViewById(R.id.btnCalc)

        mCalcButton.setOnClickListener {
            var i = mIterCount.text.toString().toInt()
            var type = findViewById<RadioButton>(mRadioType.checkedRadioButtonId).text.toString()
            calc(i, type)
        }
    }

    private fun calc(i : Int, type : String) {
        var millisPlus : Long = 0
        var millisSub : Long = 0
        var millisMul : Long = 0
        var millisDiv : Long = 0

        for (j in 1..i) {
            when(type) {
                "Integer" -> {
                    var a = Random.nextInt()
                    var b = Random.nextInt()
                    mA.text = a.toString()
                    mB.text = b.toString()

                    millisPlus += measureTimeMillis {
                        a + b
                    }

                    millisSub += measureTimeMillis {
                        a - b
                    }

                    millisMul += measureTimeMillis {
                        a * b
                    }

                    millisDiv += measureTimeMillis {
                        try {
                            a / b
                        } catch (e : Exception) {

                        }
                    }
                }
                "Long" -> {
                    var a = Random.nextLong()
                    var b = Random.nextLong()
                    mA.text = a.toString()
                    mB.text = b.toString()

                    millisPlus += measureTimeMillis {
                        a + b
                    }

                    millisSub += measureTimeMillis {
                        a - b
                    }

                    millisMul += measureTimeMillis {
                        a * b
                    }

                    millisDiv += measureTimeMillis {
                        try {
                            a / b
                        } catch (e : Exception) {

                        }
                    }
                }
                "Double" -> {
                    var a = Random.nextDouble()
                    var b = Random.nextDouble()
                    mA.text = a.toString()
                    mB.text = b.toString()

                    millisPlus += measureTimeMillis {
                        a + b
                    }

                    millisSub += measureTimeMillis {
                        a - b
                    }

                    millisMul += measureTimeMillis {
                        a * b
                    }

                    millisDiv += measureTimeMillis {
                        try {
                            a / b
                        } catch (e : Exception) {

                        }
                    }
                }
            }
        }

        mCalcPlus.text = "calc+ : $millisPlus ms"
        mCalcSub.text = "calc- : $millisSub ms"
        mCalcMul.text = "calc* : $millisMul ms"
        mCalcDiv.text = "calc/ : $millisDiv ms"
    }
}