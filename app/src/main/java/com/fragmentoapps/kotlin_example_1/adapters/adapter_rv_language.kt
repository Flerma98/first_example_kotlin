package com.fragmentoapps.kotlin_example_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fragmentoapps.kotlin_example_1.R
import com.fragmentoapps.kotlin_example_1.models.ProgramLanguage

class AdapterRVLanguage(
    private val languageList: ArrayList<ProgramLanguage>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AdapterRVLanguage.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_languages, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: ProgramLanguage = languageList[position]
        holder.titleText.text = currentItem.name
        holder.subtitleText.text = currentItem.desciption
    }

    override fun getItemCount(): Int {
        return languageList.size;
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleText = itemView.findViewById(R.id.title) as TextView
        val subtitleText = itemView.findViewById(R.id.description) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}