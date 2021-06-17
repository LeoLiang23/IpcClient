package com.example.ipcservice

import android.os.Parcel
import android.os.Parcelable

/**
 * Time:2021/6/17 11:33
 * Author: Leonardo Dicaprio
 * Description:
 */
class People() : Parcelable {

    var age: Int = 0
    var name: String = ""

    constructor(parcel: Parcel) : this() {
        age = parcel.readInt()
        name = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<People> {
        override fun createFromParcel(parcel: Parcel): People {
            return People(parcel)
        }

        override fun newArray(size: Int): Array<People?> {
            return arrayOfNulls(size)
        }
    }

}