package com.sahilbhandari.ninjareporter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahilbhandari.ninjareporter.R
import com.sahilbhandari.ninjareporter.model.UserDetails

class UserAdapter (private val partItemList: List<UserDetails>, private val listener: ItemClickListener, val context: Context) : RecyclerView.Adapter<UserAdapter.PartViewHolder>() {

    override fun getItemCount(): Int {
        return partItemList.size
    }

    companion object {
        var mClickListener: ItemClickListener? = null
    }

    interface ItemClickListener {
        fun clickToDelete(position: Int)
    }

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvExp: TextView = itemView.findViewById(R.id.tvExp)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvOcp: TextView = itemView.findViewById(R.id.tvOcp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return PartViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        val part = partItemList[position]
        if (part.experience.toInt()>1){
            holder.tvExp.text = part.experience.toString()+" YEARS"
        } else{
            holder.tvExp.text = part.experience.toString()+" YEAR"
        }
        holder.tvName.text = part.name
        holder.tvOcp.text = part.occupation
        mClickListener = listener
        holder.tvName.setOnClickListener { view ->
            mClickListener?.clickToDelete(part.id)
        }
    }

}