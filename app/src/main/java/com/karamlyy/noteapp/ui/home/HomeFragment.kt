package com.karamlyy.noteapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karamlyy.noteapp.R
import com.karamlyy.noteapp.databinding.FragmentHomeBinding
import com.karamlyy.noteapp.model.NoteModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), NoteClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.noteClickListener = this


        binding.fragmentHomeFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newAndEditFragment)
        }


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNoteClick(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewAndEditFragment(id)
        findNavController().navigate(action)
    }

    override fun onNoteChecked(noteModel: NoteModel) {
        viewModel.updateNote(noteModel)
    }


}