package brittany.varnom.com.fruitapp

import android.os.Parcel
import android.os.Parcelable

data class GroceryData(var type: String,
                       val price: Double,
                       val weight: Int,
                       val imageurl: String,
                       val kind: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeDouble(price)
        parcel.writeInt(weight)
        parcel.writeString(imageurl)
        parcel.writeString(kind)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GroceryData> {
        override fun createFromParcel(parcel: Parcel): GroceryData {
            return GroceryData(parcel)
        }

        override fun newArray(size: Int): Array<GroceryData?> {
            return arrayOfNulls(size)
        }
    }
}