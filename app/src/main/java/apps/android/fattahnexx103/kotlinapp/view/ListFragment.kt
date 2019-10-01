package apps.android.fattahnexx103.kotlinapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import apps.android.fattahnexx103.kotlinapp.R
import apps.android.fattahnexx103.kotlinapp.model.Data
import apps.android.fattahnexx103.kotlinapp.model.model
import apps.android.fattahnexx103.kotlinapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private val listAdapter = ItemListAdapter(arrayListOf())

    //we need to declare these observable variables for the viewModel to use
    private val itemListObserver = Observer<List<model>> { list ->
        list?.let {
                recyclerView.visibility = View.VISIBLE //we have data so make the view visible
                listAdapter.updateItemList(it) //and then update the list
            }
    }

    private val itemLoadingObserver = Observer<Boolean>{isItemLoaded ->
        if(isItemLoaded){ //if there is an error in loading
            list_progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            text_listError.visibility = View.GONE
        }else{
            list_progressBar.visibility = View.GONE
        }
    }

    private val itemLoadErrorObserver = Observer<Boolean>{isError ->
        if(isError){
            text_listError.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }else{
            text_listError.visibility = View.GONE
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    //method after the view is created for the fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instantiate view model
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java) //the viewmodel providers contain the view model classes

        //we create three observer functions on the three variables we have
        listViewModel.modelListObs.observe(this, itemListObserver)
        listViewModel.loadingObs.observe(this, itemLoadingObserver)
        listViewModel.loadingObs.observe(this, itemLoadErrorObserver)
        listViewModel.refresh() //this gets the items for the view Model

        //configure the recyclerView in the layout
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2) //2 columns side by side
            adapter = listAdapter //pass in the list adapter that we declated earlier
        }

        swipeRefreshLayout.setOnRefreshListener {
            //refresh the viewmodel and information
            recyclerView.visibility = View.GONE //make the UI gone
            text_listError.visibility = View.GONE // make the error text gone
            list_progressBar.visibility = View.VISIBLE //make the progressBar visible
            listViewModel.refresh() //call the refresh method
            swipeRefreshLayout.isRefreshing = false //turn off the refresh
        }
    }



}
