package com.example.zadanie.fragments

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie.interfaces.CustomClickInterface
import com.example.zadanie.viewmodels.ItemsViewModel
import com.example.zadanie.R
import com.example.zadanie.adapters.ItemsAdapter
import com.example.zadanie.databinding.FragmentItemsBinding
import com.example.zadanie.models.Items
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ItemsFragment : Fragment(R.layout.fragment_items), CustomClickInterface {

    private lateinit var binding: FragmentItemsBinding
    private lateinit var itemsAdapter: ItemsAdapter
    private val itemsViewModel: ItemsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentItemsBinding.bind(view)

        lifecycleScope.launch(Main) {
            itemsViewModel.getItemList()
            itemsViewModel.getFromBox()
            setupRecyclerView()
            val itemsObserver = Observer<MutableList<Items>> {
                itemsAdapter.setListData(itemsViewModel.itemList.value!!)
                itemsAdapter.notifyDataSetChanged()
            }
            itemsViewModel.itemList.observe(viewLifecycleOwner, itemsObserver)
        }
    }

    private fun setupRecyclerView() = binding.itemsRecyclerView.apply {
        itemsAdapter = ItemsAdapter(this@ItemsFragment)
        adapter = itemsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onClickListener(position: Int, view: View) {
        val hiddenLayout = view.findViewById<ConstraintLayout>(R.id.hidden_layout)
        hiddenLayout.visibility = if (hiddenLayout.visibility == View.GONE) View.VISIBLE else View.GONE
    }
}