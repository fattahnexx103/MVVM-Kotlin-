package apps.android.fattahnexx103.kotlinapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    // https://randomuser.me/api/?results=25
    private val baseUri = "https://randomuser.me/"

    private val api = Retrofit.Builder()
        .baseUrl(baseUri)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //converts into the Single
        .build()
        .create(RetrofitApi::class.java)


    fun getResults(): Single<Data>{
        return api.getResults(20) //get the results
    }
}