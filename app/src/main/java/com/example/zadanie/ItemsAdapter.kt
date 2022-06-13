package com.example.zadanie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class ItemsAdapter(
    private val customClickInterface: CustomClickInterface
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    private var items  = mutableListOf<Items>()

    inner class ItemsViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            apply {
                itemView.setOnClickListener {
                    customClickInterface.onClickListener(adapterPosition, itemView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.apply {
            itemName.text = items[position].name
            itemPrice.text = items[position].price.toString()
            itemCurrency.text = " " + items[position].currency
            itemTaxRate.text = "VAT: " + items[position].tax
            itemCategory.text = items[position].category
            Picasso.get().load(items[position].imageLink).into(itemImage)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setListData(data: MutableList<Items>) {
        this.items = data
    }
}
