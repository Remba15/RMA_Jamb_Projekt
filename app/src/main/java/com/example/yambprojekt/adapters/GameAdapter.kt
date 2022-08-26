package com.example.yambprojekt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yambprojekt.R

class GameAdapter(
    private val rowList: List<GameRowItem>,
    private val listener: OnCellClickListener
    ) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout,
            parent, false)

        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = rowList[position]

        holder.firstCell.text = currentItem.firstCell.mText
        holder.secondCell.text = currentItem.secondCell.mText
        holder.thirdCell.text = currentItem.thirdCell.mText
        holder.fourthCell.text = currentItem.fourthCell.mText
        holder.fifthCell.text = currentItem.fifthCell.mText
    }

    override fun getItemCount() = rowList.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val firstCell: TextView = itemView.findViewById(R.id.tv_row_name)
        val secondCell: TextView = itemView.findViewById(R.id.tv_down_cell)
        val thirdCell: TextView = itemView.findViewById(R.id.tv_up_cell)
        val fourthCell: TextView = itemView.findViewById(R.id.tv_up_down_cell)
        val fifthCell: TextView = itemView.findViewById(R.id.tv_announcement_cell)

        init {
            firstCell.setOnClickListener(this)
            secondCell.setOnClickListener(this)
            thirdCell.setOnClickListener(this)
            fourthCell.setOnClickListener(this)
            fifthCell.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onSecondCellClick(position)
                listener.onThirdCellClick(position)
                listener.onFourthCellClick(position)
                listener.onFifthCellClick(position)
            }
        }
    }


}