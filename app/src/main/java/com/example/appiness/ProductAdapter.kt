package com.example.appiness

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var dataList: List<Product>,
    private val context: MainActivity,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {


        holder.productName.text = "Name:  " + dataList.get(position).name
        holder.description_tv.text = "Description:  " + dataList.get(position).description
        holder.authentic_tv.text = "Authentic:  " + dataList.get(position).authentic
        holder.remedies_tv.text = "Remedies:  " + dataList.get(position).remedies
        holder.vedic_tv.text = "vedic:  " + dataList.get(position).vedic
        holder.price.text = "Price:  " + dataList.get(position).price.toString()
        holder.discount.text = "Discount:  " + dataList.get(position).discount.toString()





        holder.overall_layout.setOnClickListener { v ->
            val intent = Intent(v.context, Productdetailsscreen::class.java)
            intent.putExtra("name", dataList.get(position).name.toString())
            intent.putExtra("image", dataList.get(position).imagePath.toString())
            v.context.startActivity(intent)

        }

        holder.productName.setOnClickListener { v ->
            listener.onItemClick(dataList.toString(), holder.adapterPosition)
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var overall_layout: CardView
        lateinit var productName: TextView
        lateinit var description_tv: TextView
        lateinit var authentic_tv: TextView
        lateinit var remedies_tv: TextView
        lateinit var vedic_tv: TextView
        lateinit var price: TextView
        lateinit var discount: TextView


        init {
            overall_layout = itemLayoutView.findViewById(R.id.overall_layout)
            productName = itemLayoutView.findViewById(R.id.name_tv)
            description_tv = itemLayoutView.findViewById(R.id.description_tv)
            authentic_tv = itemLayoutView.findViewById(R.id.authentic_tv)
            remedies_tv = itemLayoutView.findViewById(R.id.remedies_tv)
            vedic_tv = itemLayoutView.findViewById(R.id.vedic_tv)
            price = itemLayoutView.findViewById(R.id.price)
            discount = itemLayoutView.findViewById(R.id.discount)


        }

    }

}