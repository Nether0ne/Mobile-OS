package com.nether.idz.entity

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.nether.idz.R

data class Material(
    var title: Int? = null,
    var content: Int? = null,
    var test: Test? = null) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this() {
        title = parcel.readInt()
        content = parcel.readInt()
        test = parcel.readParcelable(Test::class.java.classLoader) as Test?
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(title!!)
        parcel.writeInt(content!!)
        parcel.writeParcelable(test, 1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Material> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Material {
            return Material(parcel)
        }

        override fun newArray(size: Int): Array<Material?> {
            return arrayOfNulls(size)
        }

        fun get() : ArrayList<Material> {
            return arrayListOf(
                Material(R.string.start, R.string.start_content, Test.getTest(1)),
                Material(R.string.end, R.string.end_content, Test.getTest(2))
            )
        }
    }
}