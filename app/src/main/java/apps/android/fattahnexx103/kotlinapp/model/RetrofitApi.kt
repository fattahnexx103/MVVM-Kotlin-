package apps.android.fattahnexx103.kotlinapp.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/api/")
    fun getResults(@Query("results") results: Int): Single<Data>
}