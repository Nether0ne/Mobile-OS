package com.nether.studentapp

import android.os.Parcel
import android.os.Parcelable

class Student() : Parcelable {
    var name : String? = ""
    var phone : String? = ""
    var skipped : String? = ""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        phone = parcel.readString()
        skipped = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents() : Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(phone)
        dest?.writeString(skipped)
    }
}