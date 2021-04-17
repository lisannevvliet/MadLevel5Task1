package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import com.example.madlevel5task1.databinding.FragmentAddNoteBinding

// A simple [Fragment] subclass as the second destination in the navigation.
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
    // Fill the text fields with the current text and title from the ViewModel.
        viewModel.note.observe(viewLifecycleOwner, Observer {
                note  ->
            note?.let {
                binding.tilNoteTitle.setText(it.title)
                binding.tilNoteText.setText(it.text)
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {     success ->
            // Destroy this fragment and go back to the RemindersFragment.
            findNavController().popBackStack()
        })
    }

    private fun saveNote() {
        viewModel.updateNote(binding.tilNoteTitle.text.toString(), binding.tilNoteText.text.toString())
    }

}