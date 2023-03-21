package com.example.viewmodel1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodel1.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    // private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as MyApplication).mainViewModelFactory.create(MainViewModel::class.java) //?


        viewModel.getDogImageNetworkCall(binding.dog)

        viewModel.dogImage.observe(this) { dogImage ->
            when (dogImage) {
                is Response.Error -> Toast.makeText(this, "no", LENGTH_LONG).show()
                Response.Loading -> Toast.makeText(this, "loading", LENGTH_LONG).show()
                is Response.Success<Data> -> Picasso.get().load(dogImage.body?.message)
                    .into(binding.dog)
            }
        }

        binding.dog.visibility = View.VISIBLE
    }
}