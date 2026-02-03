package com.example.uasmobileapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uasmobileapp.R
import com.example.uasmobileapp.databinding.FragmentMainBinding
import com.example.uasmobileapp.ui.viewmodel.TaskViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe task list
        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->

            val newCount = tasks.count { it.status == "NEW" }
            val inProgressCount = tasks.count { it.status == "IN_PROGRESS" }
            val doneCount = tasks.count { it.status == "DONE" }

            binding.btnNew.text = "NEW ($newCount)"
            binding.btnInProgress.text = "IN PROGRESS ($inProgressCount)"
            binding.btnDone.text = "DONE ($doneCount)"
        }

        // Navigation with status filter
        binding.btnNew.setOnClickListener {
            navigateToList("NEW")
        }

        binding.btnInProgress.setOnClickListener {
            navigateToList("IN_PROGRESS")
        }

        binding.btnDone.setOnClickListener {
            navigateToList("DONE")
        }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainFragment_to_addTaskFragment
            )
        }
    }

    private fun navigateToList(status: String) {
        val bundle = Bundle().apply {
            putString("status", status)
        }
        findNavController().navigate(
            R.id.action_mainFragment_to_taskListFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        taskViewModel.loadTasks()
    }

}
