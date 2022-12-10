package demo.lets.work.newsapplication.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import demo.lets.work.newsapplication.R
import demo.lets.work.newsapplication.core.common.Constants
import demo.lets.work.newsapplication.databinding.FragmentNewsListBinding
import demo.lets.work.newsapplication.domain.model.News
import demo.lets.work.newsapplication.presentation.adapter.NewsAdapter
import demo.lets.work.newsapplication.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel by viewModels<NewsViewModel>()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        adapter = NewsAdapter(::onNewsClicked)
        bindingObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingObservers()

        binding.rvNewsList.layoutManager =
            LinearLayoutManager(context)
        binding.rvNewsList.setHasFixedSize(true)
        binding.rvNewsList.adapter = adapter
    }

    private fun onNewsClicked(newsItem: News) {
        val bundle = Bundle()
        bundle.putString(Constants.NEWS_URL, newsItem.newsUrl)

        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment, bundle)

    }

    private fun bindingObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.newsStateFlow.collect {

                    when (it) {
                        is NewsViewModel.NewsUiEvent.Loading -> {
                            Timber.d("Called Loading Ashif")
                            binding.progressBar.isVisible = true
                        }
                        is NewsViewModel.NewsUiEvent.Error -> {
                            Timber.d("Called Error Ashif")
                            binding.progressBar.isVisible = false
                            showToast("${it.msg}")
                        }
                        is NewsViewModel.NewsUiEvent.Success -> {
                            Timber.d("Called Success Ashif")
                            binding.progressBar.isVisible = false
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}