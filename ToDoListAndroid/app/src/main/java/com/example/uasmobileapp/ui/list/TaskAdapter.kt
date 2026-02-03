package com.example.uasmobileapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmobileapp.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private var tasks: List<Task>,
    private val onViewClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvCategory.text = "Category: ${task.category}"

            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            binding.tvTime.text = "Created: ${sdf.format(Date(task.createdTime))}"

//            when (task.status) {
//                "NEW" -> {
//                    binding.btnDetail.visibility = View.VISIBLE
//                    binding.btnDetail.text = "Take"
//                    binding.tvDuration.visibility = View.GONE
//                }
//                "IN_PROGRESS" -> {
//                    binding.btnDetail.visibility = View.VISIBLE
//                    binding.btnDetail.text = "Done"
//                    binding.tvDuration.visibility = View.GONE
//                }
//                "DONE" -> {
//                    binding.btnDetail.visibility = View.GONE
//                    binding.tvDuration.visibility = View.VISIBLE
//
//                    val duration =
//                        (task.doneTime ?: 0L) - (task.takenTime ?: task.createdTime)
//                    binding.tvDuration.text =
//                        "Duration: ${duration / 1000} seconds"
//                }
//            }

            binding.btnDetail.setOnClickListener {
                onViewClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
