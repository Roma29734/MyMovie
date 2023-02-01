package com.example.mymovie.ui.screens.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentHomeBinding
import com.example.mymovie.ui.adapter.ParentAdapter
import com.example.mymovie.ui.adapter.ViewPagerAdapter
import com.example.mymovie.utils.LoadState
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private var contex: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contex = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.textView.text = getString(R.string.Movie)
        viewPager.adapter = ViewPagerAdapter(contex as FragmentActivity)
        view.let {
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = getString(R.string.TopRated)
                    }
                    1 -> {
                        tab.text = getString(R.string.Popular)
                    }
                    2 -> {
                        tab.text = getString(R.string.NowPlaying)
                    }
                    3 -> {
                        tab.text = getString(R.string.Upcoming)
                    }
                }
            }.attach()
        }
    }
}

