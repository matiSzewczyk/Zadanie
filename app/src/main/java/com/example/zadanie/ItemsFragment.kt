package com.example.zadanie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.zadanie.databinding.FragmentItemsBinding

class ItemsFragment : Fragment(R.layout.fragment_items){

    private lateinit var binding: FragmentItemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentItemsBinding.bind(view)
    }
}