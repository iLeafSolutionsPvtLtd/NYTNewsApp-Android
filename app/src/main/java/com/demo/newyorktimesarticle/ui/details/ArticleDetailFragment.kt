package com.demo.newyorktimesarticle.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.demo.newyorktimesarticle.R
import com.demo.newyorktimesarticle.data.model.ResultsItem
import com.demo.newyorktimesarticle.databinding.FragmentArticleDetailsBinding

class ArticleDetailFragment : Fragment() {

    private val viewModel: ArticleDetailViewModel by viewModels()
    private var binding: FragmentArticleDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireArguments().getParcelable<ResultsItem>("details")?.let {
            viewModel.articleDetails = it
        }

        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)

        return binding?.apply {
            viewModel.articleDetails?.let { articleDetails ->
                tvAuthor.text = articleDetails.byline
                tvContent.text = articleDetails.jsonMemberAbstract
                tvDate.text = articleDetails.publishedDate
                context?.let {
                    var url = ""
                    if (articleDetails.media?.isNotEmpty()!!) {
                        articleDetails.media[0]?.mediaMetadata?.forEach { mediaItem ->
                            if (mediaItem?.format?.contains("440")!!)
                                url = mediaItem.url ?: ""
                        }
                    }
                    Glide.with(requireContext())
                        .load(url)
                        .placeholder(R.drawable.ic_place_holder)
                        .into(imImage)
                }
            }
        }?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
