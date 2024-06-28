package com.mutsuddi.mypractice.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mutsuddi.mypractice.R
import com.mutsuddi.mypractice.data.adapters.CharactersAdapter
import com.mutsuddi.mypractice.databinding.ActivityMainBinding
import com.mutsuddi.mypractice.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // View model for character data
    private val viewModel: CharacterViewModel by viewModels()

    // Adapter for displaying characters
    private lateinit var charactersAdapter: CharactersAdapter

    private  val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        charactersAdapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(this)
        binding.charactersRv.adapter = charactersAdapter
        //binding.charactersRv.layoutManager=LinearLayoutManager()
        binding.charactersRv.setHasFixedSize(true)

        setupObservers()
    }

    private fun setupObservers() {

        viewModel.repositories.observe(this){ response ->
            when(response)
            {

                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (!response.data.isNullOrEmpty())
                    {
                        // Toast.makeText(requireContext(), response.data, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "setupObservers: ${response.data }")

                        charactersAdapter.submitList(response.data)
                    }



                }

                is Resource.Error -> {
                    Toast.makeText(this, response.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                    // Snackbar.make(requireContext(), response.errorMessage.toString(), Snackbar.LENGTH_SHORT).show();

                }




            }

        }




    }
}