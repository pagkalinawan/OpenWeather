package com.example.openweather.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.presentation.adapter.FavoriteAdapter
import com.example.openweather.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cityList = listOf(
            "Taguig", "Makati", "Muntinlupa", "Baliuag", "Tuguegarao", "Mandaluyong",
            "San Juan", "Paranaque", "Manila", "Imus", "Bacoor"
        )

        val rvList: RecyclerView = binding.rvFavorite
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = FavoriteAdapter(cityList)

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvList.addItemDecoration(dividerItemDecoration)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}