package com.udes.profilerlab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udes.profilerlab.databinding.ItemProductBinding

// ❌ Versión ineficiente — SIN DiffUtil
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var items: List<ProductItem> = emptyList()

    // notifyDataSetChanged() redibuja TODA la lista aunque solo cambie 1 item
    fun submitList(newItems: List<ProductItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem) {
            binding.tvName.text = item.name
            binding.tvPrice.text = "$${item.price}"
            binding.tvStock.text = "Stock: ${item.stock}"
        }
    }
}