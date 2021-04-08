package com.nether.idz.entity

import android.content.Context
import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.nether.idz.R

data class Task(
        var title: Int,
        var description: Int,
        var answers: ArrayList<Int>,
        var userAnswer: String? = null,
        var right: Int = 0,
        var total: Int = 25,
        var amount: Int = 4) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            arrayListOf<Int>().apply{
                parcel.readList(this, Int::class.java.classLoader)
            },
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())

    fun calcMark(c : Context) {
        if (userAnswer != null) {
            val a = userAnswer!!.lines()

            for (i: Int in 0 until amount) {
                if (i < a.size) {
                    if (a[i] == c.resources.getString(answers[i])) {
                        right++
                    }
                } else {
                    break
                }
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(title)
        parcel.writeInt(description)
        parcel.writeList(answers)
        parcel.writeString(userAnswer)
        parcel.writeInt(right)
        parcel.writeInt(total)
        parcel.writeInt(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }

        fun get() : ArrayList<Task> {
            return arrayListOf(
                    Task(
                            R.string.task1_title,
                            R.string.task1_description,
                            arrayListOf(
                                    R.string.task1_answer1,
                                    R.string.task1_answer2,
                                    R.string.task1_answer3,
                                    R.string.task1_answer4
                            )
                    ),
                    Task(
                            R.string.task2_title,
                            R.string.task2_description,
                            arrayListOf(
                                    R.string.task2_answer1,
                                    R.string.task2_answer2,
                                    R.string.task2_answer3,
                                    R.string.task2_answer4
                            )
                    )
            )
        }
    }
}
