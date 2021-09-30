package com.example.cryptocurrencyexample.presentation.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexample.databinding.RecyclerItemCoinBinding
import com.example.cryptocurrencyexample.domain.model.Coin

class CoinListAdapter(
    private var items: List<Coin>
) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RecyclerItemCoinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size


    inner class ViewHolder(val binding: RecyclerItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Coin, position: Int) {


            binding.tvName.text = "${position}.${item.name}(${item.symbol})"
            binding.tvStatus.text = if (item.isActive) "Active" else "Not Active"


            binding.layoutRoot.setOnClickListener {
                onItemClickListener.onItemClicked(position)
            }

        }
    }

    fun setItems(items: List<Coin>) {

        this.items = items
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onItemClicked(index: Int)
    }

}