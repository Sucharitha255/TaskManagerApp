package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize with some default tasks
        tasks.addAll(listOf(
            Task("Complete Project Proposal", "Finalize the draft and send it to the team."),
            Task("Buy Groceries", "Milk, eggs, bread, and fruits."),
            Task("Workout", "Go for a 30-minute run in the park.")
        ))

        adapter = TaskAdapter(tasks)
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = adapter

        binding.addTaskButton.setOnClickListener {
            val title = binding.newTaskTitleEditText.text.toString().trim()
            val description = binding.newTaskDescriptionEditText.text.toString().trim()

            if (title.isNotEmpty()) {
                val newTask = Task(title, description)
                adapter.addTask(newTask)
                
                // Clear inputs
                binding.newTaskTitleEditText.text.clear()
                binding.newTaskDescriptionEditText.text.clear()
                
                // Scroll to the new item
                binding.tasksRecyclerView.scrollToPosition(tasks.size - 1)
            } else {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}