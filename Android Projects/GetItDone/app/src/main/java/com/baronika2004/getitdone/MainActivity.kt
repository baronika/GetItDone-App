package com.baronika2004.getitdone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baronika2004.getitdone.databinding.ActivityMainBinding
import com.baronika2004.getitdone.databinding.DialogAddTaskBinding
import com.baronika2004.getitdone.databinding.FragmentTasksBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter=PagerAdapter(this)
        TabLayoutMediator(binding.tab,binding.pager){ tab,position->
            tab.text="Tasks"

        }.attach()

        binding.fab.setOnClickListener {
            showAddTaskDialog()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(this)
            .setTitle("Add new task")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                Toast.makeText(
                    this,
                    "Your Task is:${dialogBinding.editText.text}",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    inner class PagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity){
        override fun getItemCount()=1

        override fun createFragment(position: Int): Fragment {
            return TasksFragment()
        }


    }
}