package apps.android.fattahnexx103.kotlinapp.view


import android.graphics.ColorSpace
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import apps.android.fattahnexx103.kotlinapp.R
//import apps.android.fattahnexx103.kotlinapp.databinding.FragmentDetailBinding
import apps.android.fattahnexx103.kotlinapp.model.model
import apps.android.fattahnexx103.kotlinapp.util.getProgressDrawable
import apps.android.fattahnexx103.kotlinapp.util.loadImageUri
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    var modelItem: model? = null
    //private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initiate the databinding
        //dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container,false)
        // Inflate the layout for this fragment
        //return dataBinding.root
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //if we do have the arguements
        arguments?.let {
            modelItem = DetailFragmentArgs.fromBundle(it).modelItem
            detail_imageView.loadImageUri(modelItem?.imageUrl, getProgressDrawable(view.context))
        }


        //here we pass in the values to all the views
        //we do this step now in the layout with data binding
        itemName.text = modelItem?.name
        itemDiet.text = modelItem?.diet
        itemLocation.text = modelItem?.location
        itemSpeed.text = modelItem?.speed.toString()


        //dataBinding.model = modelItem

    }

}
