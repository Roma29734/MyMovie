package com.example.mymovie.ui.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymovie.ui.screens.viewPager.popular.PopularFragment
import com.example.mymovie.ui.screens.viewPager.topReted.TopRatedFragment
import com.example.mymovie.ui.screens.viewPager.upcoming.UpcomingAdapter
import com.example.mymovie.ui.screens.viewPager.upcoming.UpcomingFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularFragment()
            1 -> TopRatedFragment()
            else -> {
                UpcomingFragment()
            }
        }
    }
}