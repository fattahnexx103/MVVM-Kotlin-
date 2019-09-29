package apps.android.fattahnexx103.kotlinapp.viewmodel

import android.app.Application
import android.graphics.ColorSpace
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apps.android.fattahnexx103.kotlinapp.model.model

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

    //create a refresh function
    fun refresh(){
        getModel() // this function is called in order to get the model
    }

    //we make this private since we only want it to be used in this class
    private fun getModel(){

        //we create 6 dummy models just to test out the application
        val dummyModel1 = model("Name 1", "Description 1")
        val dummyModel2 = model("Name 2", "Description 2")
        val dummyModel3 = model("Name 3", "Description 3")
        val dummyModel4 = model("Name 4", "Description 4")
        val dummyModel5 = model("Name 5", "Description 5")
        val dummyModel6 = model("Name 6", "Description 6")

        //now we have to add them to an arrayList
        val dummyModelList : ArrayList<model> = ArrayList<model>()
        dummyModelList.add(dummyModel1)
        dummyModelList.add(dummyModel2)
        dummyModelList.add(dummyModel3)
        dummyModelList.add(dummyModel4)
        dummyModelList.add(dummyModel5)
        dummyModelList.add(dummyModel6)

        //alternatively we could have done
        // val list: ArrayList<model> = arrayListof(dummyModel1, dummyModel2, ....)

        //add the list to the observable
        modelListObs.value = dummyModelList

        //do the rest of the observables assuming everything went well
        loadErrorObs.value = false
        loadingObs.value = false

    }

}