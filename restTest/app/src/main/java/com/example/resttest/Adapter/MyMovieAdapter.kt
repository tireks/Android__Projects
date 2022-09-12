package com.example.resttest.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.resttest.Model.Movie
import com.example.resttest.R.string
import com.example.resttest.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso
//import androidx.

class MyMovieAdapter(private val context: Context, private val movieList: MutableList<Movie>):RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding: ItemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val itemViewBinding = RowPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemViewBinding)
    }
    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
        //Picasso.get().load(movieList[position].imageurl).into(holder.image)
        //holder.txt_name.text = movieList[position].name
        //holder.txt_team.text = movieList[position].team
        //holder.txt_createdby.text = movieList[position].createdby
    }
    class MyViewHolder(private val itemViewBinding: ItemLayoutBinding): RecyclerView.ViewHolder(itemViewBinding.root){
        //val image: ImageView = itemViewBinding.image_movie
        //val txt_name: TextView = itemViewBinding.txt_name
        //val txt_team: TextView = itemViewBinding.txt_team
        //val txt_createdby: TextView = itemViewBinding.txt_createdby
        fun bind(listItem: Movie) {
            itemViewBinding.imageMovie.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemViewBinding.imageMovie}", Toast.LENGTH_SHORT)
                    .show()
            }
            itemViewBinding.txtName.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemViewBinding.txtName.text}", Toast.LENGTH_SHORT).show()
            }
            Picasso.get().load(listItem.imageurl).into(itemViewBinding.imageMovie);
            itemViewBinding.txtName.text = listItem.name;
            itemViewBinding.txtTeam.text = listItem.team;
            itemViewBinding.txtCreatedby.text = listItem.createdby;
        }
    }
}

/*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
            val itemBinding = RowPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PaymentHolder(itemBinding)
        }
  class PaymentHolder(private val itemBinding: RowPaymentBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            fun bind(paymentBean: PaymentBean) {
                itemBinding.tvPaymentInvoiceNumber.text = paymentBean.invoiceNumber
                itemBinding.tvPaymentAmount.text = paymentBean.totalAmount
            }
        }
    }


    import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeksforgeeks.rvadapterviewbinding.databinding.SingleItemBinding

class RvAdapter(
    var languageList: List<Language>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item
      // of the list languageList
      // which than will be
    // shown in recycler view
    // to keep it simple we are
      // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(languageList[position]){
                binding.tvLangName.text = this.name
                binding.tvExp.text = this.exp
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return languageList.size
    }
}*/