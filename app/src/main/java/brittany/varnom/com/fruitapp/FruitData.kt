package brittany.varnom.com.fruitapp

import android.os.Parcel
import android.os.Parcelable

data class FruitData(val fruitName: String, val fruitPrice: Double, val fruitWeight: Int, val fruitImageRes: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fruitName)
        parcel.writeDouble(fruitPrice)
        parcel.writeInt(fruitWeight)
        parcel.writeInt(fruitImageRes)
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