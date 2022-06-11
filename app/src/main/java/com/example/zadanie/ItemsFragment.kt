package com.example.zadanie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zadanie.databinding.FragmentItemsBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ItemsFragment : Fragment(R.layout.fragment_items){

    private lateinit var binding: FragmentItemsBinding
    private lateinit var itemsAdapter: ItemsAdapter
    private val itemsViewModel: ItemsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentItemsBinding.bind(view)

        val itemsObserver = Observer<Item> {
            itemsAdapter.setListData(itemsViewModel.itemList.value!!)
            itemsAdapter.notifyDataSetChanged()
        }
        itemsViewModel.itemList.observe(viewLifecycleOwner, itemsObserver)

        lifecycleScope.launch(Main) {
            itemsViewModel.getItemList()
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() = binding.itemsRecyclerView.apply {
        itemsAdapter = ItemsAdapter()
        adapter = itemsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}