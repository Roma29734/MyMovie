package com.example.mymovie.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymovie.ui.screens.home.nowPlaying.NowPlayingFragment
import com.example.mymovie.ui.screens.home.popular.PopularFragment
import com.example.mymovie.ui.screens.home.topRated.TopRatedFragment
import com.example.mymovie.ui.screens.home.upcoming.UpcomingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopRatedFragment()
            1 -> PopularFragment()
            2 -> NowPlayingFragment()
            else -> {
                UpcomingFragment()
            }
        }
    }
}