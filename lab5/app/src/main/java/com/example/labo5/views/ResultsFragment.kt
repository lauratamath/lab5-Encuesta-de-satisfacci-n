package com.example.labo5.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.labo5.R
import com.example.labo5.viewmodels.ResultsViewModel
import com.example.labo5.databinding.FragmentResultsBinding

/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    private lateinit var viewModelResults: ResultsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)
        //ViewModel
        viewModelResults = ViewModelProvider(activity!!).get(ResultsViewModel::class.java)


        binding.resultsAverage = viewModelResults.getSurveyRating().value
        binding.surveysQuantity = viewModelResults.getSurveyQuantity()


        //New survey
        binding.buttonNewSurvey.setOnClickListener{
            newSurvey()
        }
        //Show results
        binding.buttonSeeResults.setOnClickListener{
            val results = viewModelResults.getAllResults().value

            Toast.makeText(activity, "$results", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    //Generates new survey
    private fun newSurvey(){
        view!!.findNavController().navigate(R.id.action_resultsFragment_to_nav_home)
    }


}
