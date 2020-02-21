package com.sphtech.mobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sphtech.mobile.data.DataUsageYearly
import com.sphtech.mobile.R
import com.sphtech.mobile.databinding.MobileDataBinding

class MobileDataAdapter (private val mobiledata : List<DataUsageYearly>
): RecyclerView.Adapter<MobileDataAdapter.MobileDataViewHolder>(){

    override fun getItemCount() = mobiledata.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MobileDataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.mobile_data,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MobileDataViewHolder, position: Int) {
        holder.mobileDataBinding.mobiledata = mobiledata[position]

        when (mobiledata[position].decreasedQuarter) {
            "Q1" -> holder.mobileDataBinding.q1ImageView.visibility = View.VISIBLE
            "Q2" -> holder.mobileDataBinding.q2ImageView.visibility = View.VISIBLE
            "Q3" -> holder.mobileDataBinding.q3ImageView.visibility = View.VISIBLE
            "Q4" -> holder.mobileDataBinding.q4ImageView.visibility = View.VISIBLE
        }

    }

    inner class MobileDataViewHolder(
        var mobileDataBinding: MobileDataBinding
    ) : RecyclerView.ViewHolder(mobileDataBinding.root)
}