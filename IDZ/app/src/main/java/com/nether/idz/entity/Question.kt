package com.nether.idz.entity

import android.os.Parcel
import android.os.Parcelable
import com.nether.idz.R

data class Question(
    var textRes: Int? = null,
    var answers: ArrayList<Int>? = null,
    var rightAnswer: Int? = null) : Parcelable {

    constructor(parcel: Parcel) : this() {
        textRes = parcel.readInt()
        val stringArray = parcel.createIntArray()
        answers = stringArray?.toCollection(ArrayList())
        rightAnswer = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(textRes!!)
        parcel.writeIntArray(answers?.toIntArray())
        parcel.writeInt(rightAnswer!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}