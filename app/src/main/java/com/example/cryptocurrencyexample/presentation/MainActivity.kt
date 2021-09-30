package com.example.cryptocurrencyexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.cryptocurrencyexample.R
import com.example.cryptocurrencyexample.databinding.ActivityMainBinding
import com.example.cryptocurrencyexample.presentation.coin_list.CoinListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.commit {
            replace(R.id.fragment_container, CoinListFragment.newInstance())
        }


    }
}