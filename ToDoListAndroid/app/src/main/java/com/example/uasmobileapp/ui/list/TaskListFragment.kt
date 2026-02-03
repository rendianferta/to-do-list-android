package com.example.uasmobileapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uasmobileapp.R
import com.example.uasmobileapp.databinding.FragmentTaskListBinding
import com.example.uasmobileapp.ui.viewmodel.TaskViewModel

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by activityViewModels()

    private lateinit var adapter: TaskAdapter
    private var selectedCategory: String? = null
    private var currentStatus: String = "NEW"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentStatus = arguments?.getString("status") ?: "NEW"
        binding.tvStatus.text = "Status: $currentStatus"

        adapter = TaskAdapter(emptyList()) { task ->

            val bundle = Bundle().apply {
                putString("taskId", task.id)
            }

            if (task.status == "DONE") {
                // langsung ke lihat detail:
                findNavController().navigate(
                    R.id.action_taskListFragment_to_taskActionConfirmFragment,
                    bundle
                )
            } else {
                // take action:
                findNavController().navigate(
                    R.id.action_taskListFragment_to_taskActionFragment,
                    bundle
                )
            }
        }


        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_mainFragment)
        }

        binding.rvTask.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTask.adapter = adapter

        binding.btnNormal.setOnClickListener {
            selectedCategory = "Normal"
            updateList()
        }

        binding.btnUrgent.setOnClickListener {
            selectedCategory = "Urgent"
            updateList()
        }

        binding.btnImportant.setOnClickListener {
            selectedCategory = "Important"
            updateList()
        }

        taskViewModel.tasks.observe(viewLifecycleOwner) {
            updateList()
        }
    }

    private fun updateList() {
        val allTasks = taskViewModel.tasks.value ?: return

        val filteredTasks = allTasks.filter { task ->
            task.status == currentStatus &&
                    (selectedCategory == null || task.category == selectedCategory)
        }

        adapter.updateData(filteredTasks)
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
