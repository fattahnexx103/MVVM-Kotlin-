package apps.android.fattahnexx103.kotlinapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import apps.android.fattahnexx103.kotlinapp.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

//viewModel class takes in application as constrcutor and extends AndroidViewModel which takes application as parameter
//the reason why we are using androidViewModel and not viewModel is because of backend for which we need to pass a key through shared preferences
// .... class ListViewModel : viewModel .... is also ok but only when we dont need any context
class ListViewModel(application: Application) : AndroidViewModel(application) {

    //this is the viewModel for the list fragment

    //livedata variables
    //these have the observables which can be observed

    //lazy means system will not instantiate variable only when it is needed
    val modelListObs by lazy{MutableLiveData <List<model>>()} //mutable live data means it can be changed and updated
    val loadErrorObs by lazy { MutableLiveData<Boolean>() } //this is set to true and false when error is detected and changed to true and false
    val loadingObs by lazy{ MutableLiveData<Boolean>()} //this is for the progressBar to be active and inactive

    private val disposable = CompositeDisposable() //this is for maintaing the disposable data
    private val apiService = ApiService() //call the Api Service

    //create the function to get the key
    private fun getKey(){
        disposable.add(
            apiService.getKey()
                .subscribeOn(Schedulers.newThread()) //do the http stuff on a seperate new thread
                .observeOn(AndroidSchedulers.mainThread()) //whatever observables you get, put it in main thread
                .subscribeWith(object: DisposableSingleObserver<apiKey>(){ //subscribe to the disposable
                    override fun onSuccess(key: apiKey) {
                        //after the request succeeds, we get the key object
                        if(key.key.isNullOrEmpty()){
                            loadErrorObs.value = true
                            loadingObs.value = false
                        }else{
                            //if we have the key
                            getModelData(key.key)
                        }
                    }
                    override fun onError(e: Throwable) {
                        //when there is a failure
                        e.printStackTrace()
                        loadingObs.value = false
                        loadErrorObs.value = true
                    }
                })
        )

    }

    //create a refresh function
    fun refresh(){
        loadingObs.value = true
        getKey()
        //getModelData() // before we called getModel here without a param when there was no need of a key
    }

    //we make this private since we only want it to be used in this class
    private fun getModelData(key: String){

        //do the same for the other api call with the same convention
        disposable.add(
            apiService.getResults(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<model>>(){
                    override fun onSuccess(list: List<model>) {
                        loadingObs.value = false
                        modelListObs.value = list //the list is now the list value since we got a response
                        loadErrorObs.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadErrorObs.value = false
                        modelListObs.value = null //set the list to null
                        loadingObs.value = true
                    }
                })
            )


//        //we create 6 dummy models just to test out the application
//        val dummyModel1 = Model("Name 1", "Description 1")
//        val dummyModel2 = Model("Name 2", "Description 2")
//        val dummyModel3 = Model("Name 3", "Description 3")
//        val dummyModel4 = Model("Name 4", "Description 4")
//        val dummyModel5 = Model("Name 5", "Description 5")
//        val dummyModel6 = Model("Name 6", "Description 6")
//
//        //now we have to add them to an arrayList
//        val dummyModelList : ArrayList<Model> = ArrayList<Model>()
//        dummyModelList.add(dummyModel1)
//        dummyModelList.add(dummyModel2)
//        dummyModelList.add(dummyModel3)
//        dummyModelList.add(dummyModel4)
//        dummyModelList.add(dummyModel5)
//        dummyModelList.add(dummyModel6)

        //alternatively we could have done
        // val list: ArrayList<Model> = arrayListof(dummyModel1, dummyModel2, ....)

        //add the list to the observable
//        modelListObs.value = dummyModelList
//
//        //do the rest of the observables assuming everything went well
//        loadErrorObs.value = false
//        loadingObs.value = false

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}