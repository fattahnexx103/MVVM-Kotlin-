package apps.android.fattahnexx103.kotlinapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//we are going to base our Model based on this api using retrofit
//https://randomuser.me/api/?results=50

//this is the kotlin Model class which is the Java POJO Object based on the following json

//all of the classes extend the parcelable interface because of navigation purposes which requires us to pass
//in the class as a parcelable if we are to pass in the Model from one fragment to the next


//this class is for the key where we make a request to retrieve key attribute
data class apiKey(
    val message: String?,
    val key: String?
)

data class model (
    var name: String?,
    val taxonomy: Taxonomy?,
    val location: String?,
    val speed: Speed?,
    val diet: String?,
    @SerializedName("lifespan")
    val lifeSpan: String?,
    @SerializedName("image")
    val imageUrl: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Taxonomy::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Speed::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(taxonomy, flags)
        parcel.writeString(location)
        parcel.writeParcelable(speed, flags)
        parcel.writeString(diet)
        parcel.writeString(lifeSpan)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<model> {
        override fun createFromParcel(parcel: Parcel): model {
            return model(parcel)
        }

        override fun newArray(size: Int): Array<model?> {
            return arrayOfNulls(size)
        }
    }
}





data class Taxonomy(
    val kingdom: String?,
    val oder: String?,
    val family: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kingdom)
        parcel.writeString(oder)
        parcel.writeString(family)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Taxonomy> {
        override fun createFromParcel(parcel: Parcel): Taxonomy {
            return Taxonomy(parcel)
        }

        override fun newArray(size: Int): Array<Taxonomy?> {
            return arrayOfNulls(size)
        }
    }
}





data class Speed(
    val metric: String?,
    val imperial: String?
): Parcelable { //we use parcellable for navigation purposes
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(metric)
        parcel.writeString(imperial)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Speed> {
        override fun createFromParcel(parcel: Parcel): Speed {
            return Speed(parcel)
        }

        override fun newArray(size: Int): Array<Speed?> {
            return arrayOfNulls(size)
        }
    }
}

