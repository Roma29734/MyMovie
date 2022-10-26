package com.example.mymovie.ui.screens.viewPager.popular

import android.app.ProgressDialog.show
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentPopularBinding
import com.example.mymovie.utils.InternetConnection
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var adapter: PopularAdapter
    private val viewModel by viewModels<PopularViewModel>()
    private lateinit var Network: InternetConnection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)

        adapter = PopularAdapter()
        binding.mainRecycler.adapter = adapter
        binding.mainRecycler.layoutManager = GridLayoutManager(context, 3)
        Network = context?.let { InternetConnection(it) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Network.observe(viewLifecycleOwner) {connect ->
            if(connect) {
                binding.progressBar.visibility = View.INVISIBLE
                viewModel.getPopularMovieList()
                viewModel.movieList.observe(viewLifecycleOwner) { list ->
                    list?.body()?.let { adapter.setData(it.results) }
                }
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Snackbar.make(view, R.string.no_internet, Snackbar.LENGTH_SHORT)
                    .setTextColor(BLACK)
                    .setBackgroundTint(WHITE)
                    .show()
            }
        }
    }
}