package demo.lets.work.newsapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class NewsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        val textView = view.findViewById<TextView>(R.id.tvTextName)
        textView.setOnClickListener {
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment)
        }
        return view
    }


}