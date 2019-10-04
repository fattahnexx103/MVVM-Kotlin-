package apps.android.fattahnexx103.kotlinapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import apps.android.fattahnexx103.kotlinapp.R
import apps.android.fattahnexx103.kotlinapp.model.model
import apps.android.fattahnexx103.kotlinapp.util.getProgressDrawable
import apps.android.fattahnexx103.kotlinapp.util.loadImageUri
import kotlinx.android.synthetic.main.card_layout.view.*

//list adapter extends recyclerView Adapater which takes in the ViewHolder Class
class ItemListAdapter(private val modelList: ArrayList<model>):RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>(){

    // this function takes in a new list
    fun updateItemList(newItemList: List<model>){

        modelList.clear() // clear the existing list
        modelList.addAll(newItemList) // add all the items of the new list
        notifyDataSetChanged() // data changes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {


        val inflater = LayoutInflater.from(parent.context) // get the layout inflater
        val view = inflater.inflate(R.layout.card_layout, parent, false) // we inflated the card layout here
        return ItemViewHolder(view) // return the ItemViewHolder with the view passed to it
    }

    override fun getItemCount(): Int {
        return modelList.size // return the size of the array
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {


        holder.view.cardName.text = modelList[position].name // set the textview to the name of the name property
        holder.view.cardImage.loadImageUri(modelList[position].imageUrl, getProgressDrawable(holder.view.context)) //we use the method in the utils to load the images here

        //registering a click event to the card to go to the details fragment
        holder.view.itemLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(modelList[position]) //we pass in the Model at that position to the fragment
                Navigation.findNavController(holder.view).navigate(action) //call the nav controller to migrate to fragment
        }
    }

    //create the viewHolder class which extends the view holder
    class ItemViewHolder(var view: View): RecyclerView.ViewHolder(view){
    }
}