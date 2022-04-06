package com.example.cryptoappexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoappexample.model.Crypto
import com.example.cryptoappexample2.R
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val cryptoList: ArrayList<Crypto>) : RecyclerView.Adapter<RecyclerAdapter.CryptoViewHolder>() {
    class CryptoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.itemView.currency_row.text = cryptoList[position].currency
        holder.itemView.price_row.text = cryptoList[position].price
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
    fun updateCountryList(newCryptoList: List<Crypto>) {
        cryptoList.clear()
        cryptoList.addAll(newCryptoList)
        notifyDataSetChanged()
    }

}