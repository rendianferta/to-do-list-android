package com.example.uasmobileapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uasmobileapp.databinding.FragmentAddTaskBinding
import com.example.uasmobileapp.ui.list.Task
import com.example.uasmobileapp.ui.viewmodel.TaskViewModel

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDescription.text.toString()
            val category = when (binding.rgCategory.checkedRadioButtonId) {
            binding.rbNormal.id -> "Normal"
            binding.rbUrgent.id -> "Urgent"
            binding.rbImportant.id -> "Important"
            else -> {
                Toast.makeText(requireContext(), "Choose category", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

            if (title.isBlank()) {
                Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val task = Task(
                title = title,
                description = desc,
                category = category,
                status = "NEW",
                createdTime = System.currentTimeMillis()
            )


            taskViewModel.addTask(task)

            Toast.makeText(requireContext(), "Task added", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
