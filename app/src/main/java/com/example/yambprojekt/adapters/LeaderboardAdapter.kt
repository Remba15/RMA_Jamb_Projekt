package com.example.yambprojekt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yambprojekt.R
import com.example.yambprojekt.db.LeaderboardEntity

class LeaderboardAdapter() : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>(){
    private val resultList = mutableListOf<LeaderboardEntity>()

    fun setLeaderboard(results: List<LeaderboardEntity>){
        this.resultList.clear()
        this.resultList.addAll(results)
        this.notifyDataSetChanged()
    }

    fun addResult(result: LeaderboardEntity){
        this.resultList.add(result)
        notifyDataSetChanged()
    }

    fun deleteAll(){
        this.resultList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_leaderboard_layout,
            parent, false)

        return LeaderboardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val currentItem = resultList[position]

        holder.textViewName.text = currentItem.mName
        holder.textViewResult.text = currentItem.mResult
        holder.textViewDate.text = currentItem.mDate
    }

    override fun getItemCount() = resultList.size

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.tv_leaderboard_name)
        val textViewResult: TextView = itemView.findViewById(R.id.tv_leaderboard_score)
        val textViewDate: TextView = itemView.findViewById(R.id.tv_leaderboard_date)
    }
}