package com.nether.lab3.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Inventory() : Parcelable {
    // ідентифікатор інвентарю
    val mId : UUID = UUID.randomUUID()
    // назва
    var mTitle : String? = null
    // дата внесення
    var mDate : Date = Date()
    // статус
    var mSolved = false

    constructor(parcel: Parcel) : this() {
        mTitle = parcel.readString()
        mDate = Date(parcel.readString())
        mSolved = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mTitle)
        parcel.writeString(mDate.toString())
        parcel.writeByte(if (mSolved) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Inventory> {
        override fun createFromParcel(parcel: Parcel): Inventory {
            return Inventory(parcel)
        }

        override fun newArray(size: Int): Array<Inventory?> {
            return arrayOfNulls(size)
        }
    }
}
