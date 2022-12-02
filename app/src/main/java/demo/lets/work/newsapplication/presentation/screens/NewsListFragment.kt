package demo.lets.work.newsapplication.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.lets.work.newsapplication.R
import demo.lets.work.newsapplication.databinding.FragmentNewsListBinding
import demo.lets.work.newsapplication.presentation.viewmodel.NewsViewModel


@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        binding.tvTextName.setOnClickListener {
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment)
        }

        newsViewModel.getNewsHeadlines()

        return binding.root
    }


}