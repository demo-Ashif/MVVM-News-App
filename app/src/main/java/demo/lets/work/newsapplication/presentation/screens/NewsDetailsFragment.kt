package demo.lets.work.newsapplication.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.lets.work.newsapplication.R
import demo.lets.work.newsapplication.core.common.Constants
import demo.lets.work.newsapplication.databinding.FragmentNewsDetailsBinding
import demo.lets.work.newsapplication.databinding.FragmentNewsListBinding

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailNewsUrl = arguments?.getString(Constants.NEWS_URL) ?: ""

        if (detailNewsUrl.isEmpty()) {
            showToast("News detail link is not valid!")
            findNavController().popBackStack()

        } else {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(detailNewsUrl)
            }
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}