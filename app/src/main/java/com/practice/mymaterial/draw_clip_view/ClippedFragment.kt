package com.practice.mymaterial.draw_clip_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.mymaterial.R

/**
 * A simple [Fragment] subclass.
 * Use the [ClippedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClippedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clipped, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ClippedFragment().apply { }
    }
}