package com.udes.profilerlab

import android.os.Bundle
import android.os.Trace
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.udes.profilerlab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.startUpdates()
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter()
        binding.recyclerView.apply {
            this.adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(this) { products ->
            Trace.beginSection("submitList_optimized")
            adapter.submitList(products)
            Trace.endSection()
        }
    }
}