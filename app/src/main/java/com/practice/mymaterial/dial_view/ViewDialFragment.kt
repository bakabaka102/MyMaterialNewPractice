package com.practice.mymaterial.dial_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.practice.mymaterial.R
import com.practice.mymaterial.databinding.FragmentViewDialBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ViewDialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewDialFragment : Fragment() {

    private val viewModel: ViewDialViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentViewDialBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_dial, container, false)



        return binding.root
    }


    companion object {

        fun newInstance() = ViewDialFragment().apply { }
    }
}