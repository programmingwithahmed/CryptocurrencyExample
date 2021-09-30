package com.example.cryptocurrencyexample.presentation.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyexample.R
import com.example.cryptocurrencyexample.common.Constant
import com.example.cryptocurrencyexample.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrencyexample.databinding.FragmentCoinListBinding
import com.example.cryptocurrencyexample.domain.model.CoinDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel: CoinDetailViewModel by viewModels()
    private var coin: CoinDetail? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoinDetailBinding.inflate(inflater)

        setUp()
        observeData()

        return binding.root
    }

    private fun setUp() {
        binding.apply {

        }
    }

    private fun observeData() {

        viewModel.getToggleProgressBar().observe(viewLifecycleOwner, { showProgress ->
            binding.pb.visibility = if (showProgress) View.VISIBLE else View.GONE
        })

        viewModel.getShowError().observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })

        viewModel.getCoinLiveData().observe(viewLifecycleOwner, { comingCoin ->

            coin = comingCoin
            bind()
        })

    }

    private fun bind() {

        binding.apply {

            coin?.let {

                tvName.text = it.name
                tvStatus.text = if (it.isActive) "Active" else "Not Active"
                tvDesc.text = it.description

            }


        }

    }

    companion object {
        fun newInstance(coinId: String) = CoinDetailFragment().apply {
            val b = Bundle()
            b.putString(Constant.PARAM_COIN_ID, coinId)
            arguments = b
        }
    }

}