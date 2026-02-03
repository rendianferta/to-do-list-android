package com.example.uasmobileapp.ui.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uasmobileapp.databinding.FragmentTaskActionConfirmBinding
import com.example.uasmobileapp.ui.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*
import com.example.uasmobileapp.R

class TaskActionConfirmFragment : Fragment() {

    private var _binding: FragmentTaskActionConfirmBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    private var taskId: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskActionConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskId = arguments?.getString("taskId") ?: ""

        if (taskId.isEmpty()) {
            findNavController().navigateUp()
            return
        }

        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            val task = tasks.find { it.id == taskId } ?: return@observe
            showTaskDetail(task)
        }
    }

    private fun showTaskDetail(task: com.example.uasmobileapp.ui.list.Task) {

        binding.tvTitle.text = task.title
        binding.tvDescription.text = task.description
        binding.tvCategory.text = "Category: ${task.category}"
        binding.tvStatus.text = "Status: ${task.status}"

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        binding.tvCreatedTime.text =
            "Created: ${sdf.format(Date(task.createdTime))}"

        binding.btnConfirm.text =
            if (task.status == "DONE") "Back" else "Confirm"

        // Sembunyikan tombol Cancel (DONE)
        binding.btnCancel.visibility =  if (task.status == "DONE") View.GONE else View.VISIBLE

        binding.btnConfirm.setOnClickListener {

            if (task.status == "DONE") {
                findNavController().popBackStack(
                    R.id.taskListFragment,
                    false
                )
                return@setOnClickListener
            }

            val updatedTask = when (task.status) {
                "NEW" -> task.copy(
                    status = "IN_PROGRESS",
                    takenTime = System.currentTimeMillis()
                )

                "IN_PROGRESS" -> task.copy(
                    status = "DONE",
                    doneTime = System.currentTimeMillis()
                )

                else -> task
            }

            taskViewModel.updateTask(updatedTask)
            findNavController().popBackStack(
                R.id.taskListFragment,
                false
            )
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack(
                R.id.taskListFragment,
                false
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
