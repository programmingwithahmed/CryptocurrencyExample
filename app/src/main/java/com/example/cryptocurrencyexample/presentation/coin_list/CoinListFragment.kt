package com.example.cryptocurrencyexample.presentation.coin_list

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
import com.example.cryptocurrencyexample.databinding.FragmentCoinListBinding
import com.example.cryptocurrencyexample.domain.model.Coin
import com.example.cryptocurrencyexample.presentation.coin_detail.CoinDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private lateinit var binding: FragmentCoinListBinding
    private val viewModel: CoinListViewModel by viewModels()
    private val coinListAdapter: CoinListAdapter = CoinListAdapter(emptyList())
    private var coins: List<Coin> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoinListBinding.inflate(inflater)


        setUp()
        observeData()

        return binding.root
    }

    private fun setUp() {
        binding.apply {

            recyclerViewCoins.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewCoins.adapter = coinListAdapter
            coinListAdapter.onItemClickListener = object : CoinListAdapter.OnItemClickListener {

                override fun onItemClicked(index: Int) {
                    parentFragmentManager.commit {
                        add(R.id.fragment_container, CoinDetailFragment.newInstance(coins[index].id))
                    }
                }

            }

        }
    }

    private fun observeData() {

        viewModel.getToggleProgressBar().observe(viewLifecycleOwner, { showProgress ->
            binding.pb.visibility = if (showProgress) View.VISIBLE else View.GONE
        })

        viewModel.getShowError().observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })

        viewModel.getCoinsLiveData().observe(viewLifecycleOwner, { coins ->
            this.coins = coins
            coinListAdapter.setItems(coins)
        })

    }

    companion object {
        fun newInstance() = CoinListFragment()
    }

}