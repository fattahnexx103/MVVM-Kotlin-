package apps.android.fattahnexx103.kotlinapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val baseUri = "https://us-central1-apis-4674e.cloudfunctions.net"

    //initialize the client
    private val api = Retrofit.Builder()
        .baseUrl(baseUri)  //pass in base URL
        .addConverterFactory(GsonConverterFactory.create()) //this converts the data into GSON data into the class
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //converts into the Single that we are trying to use
        .build()
        .create(RetrofitApi::class.java)

    fun getKey(): Single<apiKey>{
        return api.getApiKey()
    }

    fun getResults(key: String): Single<List<model>>{
        return api.getResults(key) //get the results
    }
}