package com.oss.testmed.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oss.testmed.R


class TestAdapter(private val context: Context,
                  private val data: ArrayList<Int>) :
    RecyclerView.Adapter<TestAdapter.TestAdapterVh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapterVh {
        return TestAdapterVh(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: TestAdapterVh, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TestAdapterVh(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            val layoutParams: ViewGroup.LayoutParams = itemView.layoutParams
            layoutParams.width = 700
            itemView.layoutParams = layoutParams
        }
    }
}