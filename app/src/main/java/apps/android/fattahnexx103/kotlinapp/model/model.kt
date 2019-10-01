package apps.android.fattahnexx103.kotlinapp.model

import com.google.gson.annotations.SerializedName

//we are going to base our model based on this api using retrofit
//https://randomuser.me/api/?results=50

//this is the kotlin model class which is the Java POJO Object based on the following json


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
)

data class Taxonomy(
    val kingdom: String?,
    val oder: String?,
    val family: String?
)

data class Speed(
    val metric: String?,
    val imperial: String?
)

