package com.practice.mymaterial.spot_light

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.mymaterial.R

/**
 * A simple [Fragment] subclass.
 * Use the [SpotLightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpotLightFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spot_light, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpotLightFragment().apply { }
    }
}