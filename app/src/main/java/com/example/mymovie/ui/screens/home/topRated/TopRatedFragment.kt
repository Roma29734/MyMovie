package com.example.mymovie.ui.screens.home.topRated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentTopRatedBinding
import com.example.mymovie.ui.adapter.MoviePagingAdapter
import com.example.mymovie.ui.screens.home.SharedViewModel
import com.example.mymovie.ui.view.BottomSheetDialogMovieDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedFragment :
    BaseFragment<FragmentTopRatedBinding>
        (FragmentTopRatedBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter = MoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.showBottomSheetDialog = {
//            val action = TopRatedFragmentDirections.actionTopRatedFragmentToDetailFragment(it)
//            val actionOne = Navigation.findNavController(view).navigate(action)

            val bottomSheet = BottomSheetDialogMovieDetail(it)
            bottomSheet.show(childFragmentManager, "aboba")
        }

        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter = adapter
        lifecycleScope.launch (Dispatchers.IO) {
            viewModel.getTopRatedFlow().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}