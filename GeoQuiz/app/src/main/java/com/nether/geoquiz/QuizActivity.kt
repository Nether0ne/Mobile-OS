package com.nether.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class QuizActivity : AppCompatActivity() {
    private lateinit var mTrueButton : Button
    private lateinit var mFalseButton : Button
    private lateinit var mNextButton : ImageButton
    private lateinit var mPrevButton : ImageButton
    private lateinit var mTextQuiz : TextView
    private var mQuizScore : Int = 0
    private val TAG = "QuizActivity"
    private val KEY_INDEX = "index"

    private lateinit var mQuestionBank : ArrayList<Question>

    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        mTextQuiz = findViewById(R.id.TextQuiz)
        mQuestionBank = Question.get(4)
        updateQuestion()
        addListenerOnClickButtonTrueFalse()
    }

    private fun addListenerOnClickButtonTrueFalse() {
        mTrueButton = findViewById(R.id.buttonTrue)
        mFalseButton = findViewById(R.id.buttonFalse)
        mNextButton = findViewById(R.id.buttonNext)
        mPrevButton = findViewById(R.id.buttonPrev)
        mTextQuiz = findViewById(R.id.TextQuiz)

        mTrueButton.setOnClickListener {
            checkAnswer(true)
            // pop question
            mQuestionBank.removeAt(mCurrentIndex)
            // set answer buttons invisible
            mTrueButton.visibility = View.INVISIBLE
            mFalseButton.visibility = View.INVISIBLE
        }
        mFalseButton.setOnClickListener {
            checkAnswer(false)
            // pop question
            mQuestionBank.removeAt(mCurrentIndex)
            // set answer buttons invisible
            mTrueButton.visibility = View.INVISIBLE
            mFalseButton.visibility = View.INVISIBLE
        }
        mNextButton.setOnClickListener {
            // on moving to next question set answer buttons visible
            mTrueButton.visibility = View.VISIBLE
            mFalseButton.visibility = View.VISIBLE

            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }
        mPrevButton.setOnClickListener {
            // on moving to next question set answer buttons visible
            mTrueButton.visibility = View.VISIBLE
            mFalseButton.visibility = View.VISIBLE

            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.size
            if (mCurrentIndex < 0)
                mCurrentIndex = mQuestionBank.size - 1
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        mTextQuiz.setText(mQuestionBank[mCurrentIndex].mTextResId)
        mTextQuiz.setTextColor(resources.getColor(R.color.design_default_color_on_secondary))
    }

    private fun checkAnswer(userPressedTrue : Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue

        if (userPressedTrue == answerIsTrue) {
            mTextQuiz.setText(R.string.toast_true);
            mTextQuiz.setTextColor(Color.GREEN)
            mQuizScore++
        } else {
            mTextQuiz.setText(R.string.toast_false);
            mTextQuiz.setTextColor(Color.RED)
        }

        Toast.makeText(this, ("Total score: $mQuizScore"), Toast.LENGTH_SHORT).apply {setGravity(Gravity.TOP, 0, 0); show()}
    }
}
