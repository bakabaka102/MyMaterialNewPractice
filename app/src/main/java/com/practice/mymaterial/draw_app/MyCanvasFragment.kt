package com.practice.mymaterial.draw_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import com.practice.mymaterial.R
import com.practice.mymaterial.databinding.FragmentMyCanvasBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MyCanvasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyCanvasFragment : Fragment() {

    private var binding: FragmentMyCanvasBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myCanvasView = MyCanvasView(requireActivity())
        // No XML file; just one custom view created programmatically.
        // Request the full available screen for layout.
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)

//        binding = FragmentMyCanvasBinding.inflate(inflater, container, false)
//        return binding?.root
        return myCanvasView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = MyCanvasFragment().apply { }
    }
}