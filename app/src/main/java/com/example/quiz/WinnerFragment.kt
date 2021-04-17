package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz.databinding.FragmentWinnerBinding


private var _binding: FragmentWinnerBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!



class WinnerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWinnerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



}