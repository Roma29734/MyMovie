package com.example.mymovie.ui.screens.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentHomeBinding
import com.example.mymovie.ui.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private var contex: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contex = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.include.textView.text = "Movie"
        binding.viewPager.adapter = HomeViewPagerAdapter(contex as FragmentActivity)

        TabLayoutMediator(view.tableLayout, view.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Популярные фильмы"
                }
                1 -> {
                    tab.text = "Лучшие рейтинги"
                }
                2 -> {
                    tab.text = "Предстоящие"
                }
            }
        }.attach()
    }
}

