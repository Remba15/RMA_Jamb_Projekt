package com.example.yambprojekt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yambprojekt.R
import com.example.yambprojekt.adapters.GameAdapter
import com.example.yambprojekt.adapters.GameRowItem
import com.example.yambprojekt.adapters.OnCellClickListener
import com.example.yambprojekt.data.Die
import com.example.yambprojekt.data.TableCell
import com.example.yambprojekt.data.getDieResource
import com.example.yambprojekt.databinding.ActivityGameRecyclerViewBinding

val mFirstCellList = List(17){TableCell()}
val mSecondCellList = List(17){TableCell()}
val mThirdCellList = List(17){TableCell()}
val mFourthCellList = List(17){TableCell()}
val mFifthCellList = List(17){TableCell()}
val list = ArrayList<GameRowItem>()
val mDieList = List(6){Die(1); Die(2); Die(3); Die(4); Die(5);
        Die(6)}



class GameActivity : AppCompatActivity(), OnCellClickListener {
    private var mGameRowList = generateRows()
    private lateinit var dice: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameRecyclerViewBinding.inflate(layoutInflater).also{
            setContentView(it.root)
            it.btnRoll.setOnClickListener { rollDice() }
            dice = listOf(
                it.ivDieOne, it.ivDieTwo, it.ivDieThree, it.ivDieFour, it.ivDieFive, it.ivDieSix
            )
        }

        binding.btnRoll.setOnClickListener{rollDice()}
        binding.rvMainTable
        binding.rvMainTable.adapter = GameAdapter(mGameRowList, this)
        binding.rvMainTable.layoutManager = LinearLayoutManager(this)
        binding.rvMainTable.hasFixedSize()

    }

    private fun rollDice(){
        for(die in mDieList){
            if(!die.mLocked){
                die.throwDie()
                showResult(die)
            }
        }
    }

    private fun showResult(die: Die) {
        mDieList.forEachIndexed { index, die ->
            dice[index].setImageResource(getDieResource(die.mNumber))
        }
    }

    /*private fun enterCellInfo(rowNumber: Int, cellNumber: Int, cellText: String){
        if(cellNumber == 1){
            list[rowNumber - 1].firstCell.mText = cellText
            list[rowNumber - 1].firstCell.fillCell()
        }
        if(cellNumber == 2){
            list[rowNumber - 1].secondCell.mText = cellText
            list[rowNumber - 1].secondCell.fillCell()
        }
        if(cellNumber == 3){
            list[rowNumber - 1].thirdCell.mText = cellText
            list[rowNumber - 1].thirdCell.fillCell()
        }
        if(cellNumber == 4){
            list[rowNumber - 1].fourthCell.mText = cellText
            list[rowNumber - 1].fourthCell.fillCell()
        }
        if(cellNumber == 5){
            list[rowNumber - 1].fourthCell.mText = cellText
            list[rowNumber - 1].fourthCell.fillCell()
        }
    }*/

    private fun generateRows(): List<GameRowItem> {
        val rowNamesList = arrayOf("1*", "2", "3", "4", "5", "6", "∑\n(>= 60\n+30)", "MAX",
            "MIN", "∑\n(MAX-MIN)x*", "2 PAIR", "TRIS", "STRAIGHT" , "FULL" , "POKER", "YAMB", "∑")

        for(i in 0..16){
            list.add(GameRowItem(mFirstCellList[i], mSecondCellList[i], mThirdCellList[i],
                mFourthCellList[i], mFifthCellList[i]))
        }

        for(i in 0..16){
            list[i].firstCell.mText = rowNamesList[i]
        }

        return list
    }

    override fun onCellClick(position: Int) {
        val clickedItem: GameRowItem = mGameRowList[position]
    }

}