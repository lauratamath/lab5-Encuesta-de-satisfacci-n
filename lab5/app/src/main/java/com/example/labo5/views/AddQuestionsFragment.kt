package com.example.labo5.views


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.labo5.viewmodels.QuestionsViewModel
import com.example.labo5.R
import com.example.labo5.databinding.FragmentAddQuestionsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_questions.*

/**
 * A simple [Fragment] subclass.
 */
class AddQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentAddQuestionsBinding
    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add_questions, container, false)
        //Keyboard
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        //Menu
        setHasOptionsMenu(true)

        // Get the viewModel
        viewModel = ViewModelProvider(activity!!).get(QuestionsViewModel::class.java)

        return binding.root
    }
    //Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_question, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        // Get the viewModel
        viewModel = ViewModelProvider(activity!!).get(QuestionsViewModel::class.java)
        //Get question from edit text
        val lastQuestion = EditTextNewQuestion.getText().toString()
        //Set new question
        if (item.itemId == R.id.saveQuestion){
            viewModel.addQuestion(lastQuestion)

            Toast.makeText(activity, "$lastQuestion was added", Toast.LENGTH_SHORT).show()
            EditTextNewQuestion.getText().clear()
        }


        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
    }
}
