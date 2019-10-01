package apps.android.fattahnexx103.kotlinapp.model

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitApi {

    //these are the retrofit methods we call in order to retrieve the api

    //get the api key call which returns an api key

    @GET("getKey") //getKey is the endpoint
    fun getApiKey(): Single<apiKey>  // a single is an observable where we get one response and finishes since we are just calling it once

    //we are making a post request where we are sending in the key
    @FormUrlEncoded //this needs to be called in order to use @Field
    @POST("getAnimals") //endpoint is getAnimals
    fun getResults(@Field("key") key: String): Single<List<model>> //we get the results which is a List of the model and we pass in the key
}