package com.example.yambprojekt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yambprojekt.R
import com.example.yambprojekt.adapters.GameAdapter
import com.example.yambprojekt.adapters.GameRowItem
import com.example.yambprojekt.adapters.OnCellClickListener
import com.example.yambprojekt.data.Die
import com.example.yambprojekt.data.TableCell
import com.example.yambprojekt.data.getDieResource
import com.example.yambprojekt.data.getLockedDieResource
import com.example.yambprojekt.databinding.ActivityGameRecyclerViewBinding

val mFirstCellList = List(17){TableCell()}
val mSecondCellList = List(17){TableCell()}
val mThirdCellList = List(17){TableCell()}
val mFourthCellList = List(17){TableCell()}
val mFifthCellList = List(17){TableCell()}
val list = ArrayList<GameRowItem>()
val mDieList = List(6){Die(0); Die(1); Die(2); Die(3); Die(4);
        Die(5)}



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
            it.ivDieOne.setOnClickListener{lockUnlockDie(0)}
            it.ivDieTwo.setOnClickListener{lockUnlockDie(1)}
            it.ivDieThree.setOnClickListener{lockUnlockDie(2)}
            it.ivDieFour.setOnClickListener{lockUnlockDie(3)}
            it.ivDieFive.setOnClickListener{lockUnlockDie(4)}
            it.ivDieSix.setOnClickListener{lockUnlockDie(5)}
        }

        binding.rvMainTable
        binding.rvMainTable.adapter = GameAdapter(mGameRowList, this)
        binding.rvMainTable.layoutManager = LinearLayoutManager(this)
        binding.rvMainTable.hasFixedSize()

    }

    private fun rollDice(){
        for(die in mDieList){
            die.throwDie()
            showResult()
        }
    }

    private fun showResult() {
        mDieList.forEachIndexed { index, die ->
            if(!die.mLocked) dice[index].setImageResource(getDieResource(die.mNumber))
        }
    }

    private fun lockUnlockDie(id: Int){
        if(!mDieList[id].mLocked){
            mDieList[id].lockDie()
            dice[id].setImageResource(getLockedDieResource(mDieList[id].mNumber))
        }
        else{
            mDieList[id].unlockDie()
            dice[id].setImageResource(getDieResource(mDieList[id].mNumber))
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
        val rowNamesList = arrayOf("1*", "2", "3", "4", "5", "6", "∑\n(>= 60\n+30)", "Max",
            "Min", "∑\n(max-min) x *", "2 para", "Tris", "Skala" , "Full" , "Poker", "Yamb", "∑")

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

    }

}