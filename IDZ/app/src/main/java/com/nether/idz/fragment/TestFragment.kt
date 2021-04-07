package com.nether.idz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.entity.Question
import com.nether.idz.entity.Test

class TestFragment(private val test: Test): Fragment() {
    private lateinit var questionText: TextView
    private lateinit var answerGroup: RadioGroup
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton
    private lateinit var answer4: RadioButton
    private lateinit var btnNext: Button
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_test, container, false)

        questionText = view.findViewById(R.id.testQuestion)

        answerGroup = view.findViewById(R.id.radioAnswers)
        answer1 = view.findViewById(R.id.radioOption1)
        answer2 = view.findViewById(R.id.radioOption2)
        answer3 = view.findViewById(R.id.radioOption3)
        answer4 = view.findViewById(R.id.radioOption4)

        setData()

        btnNext = view.findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            val answer = when(answerGroup.checkedRadioButtonId) {
                R.id.radioOption1 -> 1
                R.id.radioOption2 -> 2
                R.id.radioOption3 -> 3
                R.id.radioOption4 -> 4
                else -> -1
            }

            if (test.questions?.get(index)?.rightAnswer!! == answer) {
                test.right++
                Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Не правильно!", Toast.LENGTH_SHORT).show()
            }

            index += 1
            setData()
        }

        return view
    }

    private fun setData() {
        if (index < test.questions?.size!!) {
            answerGroup.clearCheck()
            val currentQuestion : Question? = test.questions?.get(index)

            questionText.text = this.resources.getText(currentQuestion?.textRes!!)
            answer1.text = this.resources.getText(currentQuestion.answers?.get(0)!!)
            answer2.text = this.resources.getText(currentQuestion.answers?.get(1)!!)
            answer3.text = this.resources.getText(currentQuestion.answers?.get(2)!!)
            answer4.text = this.resources.getText(currentQuestion.answers?.get(3)!!)

            if (index == test.questions?.size!! - 1) {
                btnNext.text = this.resources.getText(R.string.test_end)
            }

        } else {
            // return to previous activity
            endTest()
        }
    }

    private fun endTest() {
        val intent = Intent()
        intent.putExtra("test", test)
        activity?.setResult(Activity.RESULT_OK, intent)
        activity?.finish()
    }
}