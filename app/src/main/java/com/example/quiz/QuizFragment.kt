package com.example.quiz

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.quiz.databinding.FragmentQuizBinding
class QuizFragment : Fragment(),View.OnClickListener {

    private var hasAnswer: Boolean = false
    private var correctCount: Int = 0
    private var correctAnswerIndex: Int = 0
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var currentIndex = 0
    private var numQuestions:Int = 0
    lateinit var binding: FragmentQuizBinding
    lateinit var buttonHashMap:HashMap<Int,Button>
    lateinit var color:Color

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQuizBinding>(
                inflater, R.layout.fragment_quiz, container, false)


        initData()
        initView()
        binding.data = this
        setButtonAction()

        return binding.root
    }

    private fun initView() {

        buttonHashMap = HashMap<Int,Button>()

        binding.apply{
            buttonHashMap[0] = aBtn
            buttonHashMap[1] = bBtn
            buttonHashMap[2] = cBtn
            buttonHashMap[3] = dBtn

        }
        setButtonColor()
    }

    private fun setButtonColor() {
        for((index,button)in buttonHashMap){
            button.setBackgroundColor(Color.BLUE)
        }
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
        correctAnswerIndex = answers.indexOf(currentQuestion.answers[0])
        Log.i("correctAnswerIndex",correctAnswerIndex.toString())
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
        when(v?.id){
            R.id.aBtn -> checkAnswer(0)
            R.id.bBtn -> checkAnswer(1)
            R.id.cBtn -> checkAnswer(2)
            R.id.dBtn -> checkAnswer(3)
        }

    }

    private fun checkAnswer(i: Int) {
        if(!hasAnswer){
            //if it has not been answer before check the answer
            //and update ui
            Log.i("correct?","${i==correctAnswerIndex}")
            buttonHashMap[correctAnswerIndex]?.apply {
                setBackgroundColor(Color.GREEN)
            }
            if(i==correctAnswerIndex){
                correctCount++
            }else{
                buttonHashMap[i]?.setBackgroundColor(Color.RED)
            }
        }else{
            currentIndex++
            if(currentIndex< questions.size){
                setQuestion()
                setButtonColor()
                binding.invalidateAll()
            }else{
                //TODO check the score

            }



        }

        hasAnswer = !hasAnswer
    }


}