package android.example.com.bottomapp

/**
 *Created by Ousmane on 3/4/2019.
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_child.*

class ChildFragment : androidx.fragment.app.Fragment() {

    companion object {
        const val KEY = "FragmentKey"
        fun newInstance(key: String): androidx.fragment.app.Fragment {
            val fragment = ChildFragment()
            val argument = Bundle()
            argument.putString(KEY, key)
            fragment.arguments = argument
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            child_text_title.text = it.getString(KEY)
        }
    }
}