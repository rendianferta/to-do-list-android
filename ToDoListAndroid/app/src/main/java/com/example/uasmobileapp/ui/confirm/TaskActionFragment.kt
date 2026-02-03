package com.example.uasmobileapp.ui.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uasmobileapp.R
import com.example.uasmobileapp.databinding.FragmentTaskActionBinding
import com.example.uasmobileapp.ui.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class TaskActionFragment : Fragment() {

    private var _binding: FragmentTaskActionBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    private var taskId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskId = arguments?.getString("taskId") ?: ""

        if (taskId.isEmpty()) {
            findNavController().navigateUp()
            return
        }

        // ambil task berdasarkan id
        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            val task = tasks.find { it.id == taskId } ?: return@observe
            showTask(task)
        }

        binding.btnAction.setOnClickListener {
            val bundle = Bundle().apply {
                putString("taskId", taskId)
            }

            findNavController().navigate(
                R.id.action_taskActionFragment_to_taskActionConfirmFragment,
                bundle
            )
        }
    }

    private fun showTask(task: com.example.uasmobileapp.ui.list.Task) {
        binding.tvTitle.text = task.title
        binding.tvCategory.text = "Category: ${task.category}"

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        binding.tvTime.text = "Created: ${sdf.format(Date(task.createdTime))}"

        binding.btnAction.text = when (task.status) {
            "NEW" -> "Take"
            "IN_PROGRESS" -> "Done"
            else -> "Action"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
