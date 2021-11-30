package com.demo.newyorktimesarticle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.newyorktimesarticle.data.model.ResultsItem
import com.demo.newyorktimesarticle.databinding.FragmentHomeBinding
import com.demo.newyorktimesarticle.utils.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var articleItemAdapter: ArticleItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpViews()
        observeArticleData()

        return root
    }

    private fun observeArticleData() {
        viewModel.getArticles().observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> {
                    binding.pbLoading.visibility = View.GONE
                }
                is State.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is State.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data.results?.let {
                        articleItemAdapter.setArticleItemList(it)
                    }
                }
            }
        }
    }

    private fun setUpViews() {
        binding.rvArticles.layoutManager = LinearLayoutManager(context)
        articleItemAdapter = ArticleItemAdapter(context) {
            navigateToDetailFragment(it)
        }
        binding.rvArticles.adapter = articleItemAdapter
    }

    private fun navigateToDetailFragment(it: ResultsItem) {
        binding.root.findNavController()
            .navigate(HomeFragmentDirections.homeToArticleDetails(it))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
