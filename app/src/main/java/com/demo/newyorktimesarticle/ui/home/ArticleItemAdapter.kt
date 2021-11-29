package com.demo.newyorktimesarticle.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.newyorktimesarticle.BR
import com.demo.newyorktimesarticle.R
import com.demo.newyorktimesarticle.data.model.ResultsItem
import com.demo.newyorktimesarticle.databinding.LayoutItemArticleBinding

class ArticleItemAdapter(private val context: Context?, private val onForwardClick: (ResultsItem) -> Unit) : RecyclerView.Adapter<ArticleItemAdapter.ViewHolder>() {
    private var articleList: MutableList<ResultsItem?> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_item_article, parent, false
        )
        return ViewHolder(binding)
    }

    fun setArticleItemList(_articleList: List<ResultsItem?>) {
        articleList.clear()
        articleList.addAll(_articleList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ViewHolder(var itemRowBinding: LayoutItemArticleBinding) : RecyclerView.ViewHolder(
        itemRowBinding.root
    ) {
        fun bind(content: ResultsItem?) {
            itemRowBinding.setVariable(BR.article, content)
            loadImage(content)
            itemRowBinding.clArticleView.setOnClickListener {
                content?.let { onForwardClick.invoke(content) }
            }
            itemRowBinding.executePendingBindings()
        }

        private fun loadImage(
            content: ResultsItem?
        ) {
            context?.let {
                var url = ""
                if (content?.media?.isNotEmpty()!!) {
                    content?. media[0]?.mediaMetadata?.forEach { item ->
                        if (item?.format == "Standard Thumbnail")
                            url = item.url ?: ""
                    }
                }
                Glide.with(context)
                    .load(url)
                    .circleCrop()
                    .placeholder(R.drawable.ic_place_holder)
                    .into(itemRowBinding.ivThumbnail)
            }
        }
    }
}
