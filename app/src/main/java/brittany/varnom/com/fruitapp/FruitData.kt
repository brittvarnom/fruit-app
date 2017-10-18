package brittany.varnom.com.fruitapp

import android.os.Parcel
import android.os.Parcelable

data class FruitData(val type: String, val price: Double, val weight: Int, val imageRes: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeDouble(price)
        parcel.writeInt(weight)
        parcel.writeInt(imageRes)
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