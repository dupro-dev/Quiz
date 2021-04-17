package com.example.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quiz.databinding.FragmentQuizBinding
class QuizFragment : Fragment(),View.OnClickListener {

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    var currentIndex = 0
    var numQuestions:Int = 0
    lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQuizBinding>(
                inflater, R.layout.fragment_quiz, container, false)

        initData()
        binding.data = this
        setButtonAction()

        return binding.root
    }

    private fun setButtonAction() {
        binding.aBtn.setOnClickListener(this)
        binding.bBtn.setOnClickListener(this)
        binding.cBtn.setOnClickListener(this)
        binding.dBtn.setOnClickListener(this)
    }

    private fun initData() {
        initAnswer()
        numQuestions = questions.size
        setQuestion()

    }

    private fun setQuestion() {
        currentQuestion = questions[currentIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz (${currentIndex+1}/$numQuestions)"
    }

    private fun initAnswer() {
        answers = mutableListOf();
        for(q in questions){
            answers.add(q.answers[0])
        }
        Log.i("Answer",answers.toString());
    }

    override fun onClick(v: View?) {
        Log.i("view",v?.id.toString())
        val button = view as Button
        Log.i("button-text",button.text.toString())
    }


}