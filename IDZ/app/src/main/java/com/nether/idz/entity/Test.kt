package com.nether.idz.entity

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.nether.idz.R

data class Test(
    var total: Int = 25,
    var amount: Int = 4,
    var right: Int = 0,
    var questions: ArrayList<Question>? = null,
    var done: Boolean = false
) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this() {
        total = parcel.readInt()
        amount = parcel.readInt()
        right = parcel.readInt()
        questions = arrayListOf<Question>().apply{
            parcel.readList(this, Question::class.java.classLoader)
        }
        done = parcel.readBoolean()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(total)
        parcel.writeInt(amount)
        parcel.writeInt(right)
        parcel.writeList(questions)
        parcel.writeBoolean(done)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Test> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Test {
            return Test(parcel)
        }

        override fun newArray(size: Int): Array<Test?> {
            return arrayOfNulls(size)
        }

        fun getTest(index: Int) : Test {
            when(index) {
                1 -> {
                    return Test(
                            25,
                            4,
                            0,
                            arrayListOf(
                                    Question(
                                            R.string.test1_question1,
                                            arrayListOf(
                                                    R.string.test1_question_1_answer1,
                                                    R.string.test1_question_1_answer2,
                                                    R.string.test1_question_1_answer3,
                                                    R.string.test1_question_1_answer4),
                                            3),
                                    Question(
                                            R.string.test1_question2,
                                            arrayListOf(
                                                    R.string.test1_question_2_answer1,
                                                    R.string.test1_question_2_answer2,
                                                    R.string.test1_question_2_answer3,
                                                    R.string.test1_question_2_answer4),
                                            1),
                                    Question(
                                            R.string.test1_question3,
                                            arrayListOf(
                                                    R.string.test1_question_3_answer1,
                                                    R.string.test1_question_3_answer2,
                                                    R.string.test1_question_3_answer3,
                                                    R.string.test1_question_3_answer4),
                                            2),
                                    Question(
                                            R.string.test1_question4,
                                            arrayListOf(
                                                    R.string.test1_question_4_answer1,
                                                    R.string.test1_question_4_answer2,
                                                    R.string.test1_question_4_answer3,
                                                    R.string.test1_question_4_answer4),
                                            4)
                            )
                    )
                }
                2 -> {
                    return Test(
                            25,
                            4,
                            0,
                            arrayListOf(
                                    Question(
                                            R.string.test2_question1,
                                            arrayListOf(
                                                    R.string.test2_question_1_answer1,
                                                    R.string.test2_question_1_answer2,
                                                    R.string.test2_question_1_answer3,
                                                    R.string.test2_question_1_answer4),
                                            3),
                                    Question(
                                            R.string.test2_question2,
                                            arrayListOf(
                                                    R.string.test2_question_2_answer1,
                                                    R.string.test2_question_2_answer2,
                                                    R.string.test2_question_2_answer3,
                                                    R.string.test2_question_2_answer4),
                                            4),
                                    Question(
                                            R.string.test2_question3,
                                            arrayListOf(
                                                    R.string.test2_question_3_answer1,
                                                    R.string.test2_question_3_answer2,
                                                    R.string.test2_question_3_answer3,
                                                    R.string.test2_question_3_answer4),
                                            2),
                                    Question(
                                            R.string.test2_question4,
                                            arrayListOf(
                                                    R.string.test2_question_4_answer1,
                                                    R.string.test2_question_4_answer2,
                                                    R.string.test2_question_4_answer3,
                                                    R.string.test2_question_4_answer4),
                                            2)
                            )
                    )
                }
            }

            return Test()
        }
    }
}