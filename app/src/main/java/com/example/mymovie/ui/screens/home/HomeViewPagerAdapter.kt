package com.example.mymovie.ui.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymovie.ui.screens.favourites.FavouritesFragment
import com.example.mymovie.ui.screens.popular.PopularFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularFragment()
            else -> {
                FavouritesFragment()
            }
        }
    }
}