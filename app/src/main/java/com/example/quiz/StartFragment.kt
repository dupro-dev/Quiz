package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.quiz.databinding.FragmentStartBinding

private var _binding: FragmentStartBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        /*binding.startBtn.setOnClickListener { v: View? ->
            view.findNavController().navigate(R.id.action_startFragment_to_quizFragment)

        }*/

        binding.startBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_quizFragment))
        return view
    }


}