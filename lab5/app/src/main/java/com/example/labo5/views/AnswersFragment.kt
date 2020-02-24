package com.example.labo5.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.labo5.viewmodels.QuestionsViewModel
import com.example.labo5.R
import com.example.labo5.viewmodels.ResultsViewModel
import com.example.labo5.databinding.FragmentAnswersBinding
import kotlinx.android.synthetic.main.fragment_answers.*


class AnswersFragment : Fragment() {
    private lateinit var binding: FragmentAnswersBinding
    private lateinit var viewModel: QuestionsViewModel
    private lateinit var viewModelResults: ResultsViewModel
    private var showResults = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_answers, container, false)
        binding.ratingBar.setVisibility(View.GONE)

        // Get the viewModel
        viewModel = ViewModelProvider(activity!!).get(QuestionsViewModel::class.java)
        viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)

        viewModel.defaultQuestions()

        //Keyboard
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        binding.question = viewModel.getFirstQuestion()
        viewModel.nextQuestion()
        Log.i("RatingAnswersFragment", "SIZE ANTES" +viewModel.getQuestionList().size)

        //Next questions
        binding.buttonNextQuestion.setOnClickListener {
            viewModel.nextQuestion()

            if (showResults) {
                viewModelResults.setRating(binding.ratingBar.rating)

                Log.i("RatingAnswersFragment", "Rating" + binding.ratingBar.rating)
                view!!.findNavController().navigate(R.id.action_answersFragment_to_resultsFragment)

                viewModelResults.setAnswers(binding.ratingBar.rating.toString())

            } else if (viewModel.getQuestionList().size == 0) { //Shows rating
                updateQuestion()
                showResults = true
                binding.editTextAnswer.setVisibility(View.GONE)
                binding.ratingBar.setVisibility(View.VISIBLE)
            } else {
                updateQuestion()
            }
            Log.i("RatingAnswersFragment", "SIZE DESPUES" +viewModel.getQuestionList().size)

            getAnswer()
        }
        return binding.root
    }
    //Update questions
    private fun updateQuestion() {
        binding.question = viewModel.question
    }
    //Get answers
    private fun getAnswer(){
        viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)
        //Get answer from edit text
        val lastAnswer = editTextAnswer.getText().toString()

        viewModelResults.setAnswers(lastAnswer)

        editTextAnswer.getText().clear()
    }


}
