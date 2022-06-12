package com.example.zadanie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class ItemsAdapter(
    private val customClickInterface: CustomClickInterface
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    private var items  = Item(null)

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
            itemName.text = items.data?.get(position)?.name
            itemPrice.text = items.data?.get(position)?.price?.amount.toString()
            itemCurrency.text = items.data?.get(position)?.price?.currency.toString()
            Picasso.get().load(items.data?.get(position)?.image_link?.small).into(itemImage)
            itemTax.text = items.data?.get(position)?.tax?.name.toString()
        }
    }

    override fun getItemCount(): Int {
        return items.data!!.size
    }

    fun setListData(data: Item) {
        this.items = data
    }
}
