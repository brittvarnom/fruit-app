package brittany.varnom.com.fruitapp

import android.os.Parcel
import android.os.Parcelable

data class VegData(var type: String,
                   val price: Double,
                   val weight: Int,
                   val imageurl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeDouble(price)
        parcel.writeInt(weight)
        parcel.writeString(imageurl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FruitData> {
        override fun createFromParcel(parcel: Parcel): FruitData {
            return FruitData(parcel)
        }

        override fun newArray(size: Int): Array<FruitData?> {
            return arrayOfNulls(size)
        }
    }
}