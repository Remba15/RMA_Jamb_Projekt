package com.example.yambprojekt.activities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
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
import kotlin.math.sqrt

val mFirstCellList = List(17){TableCell()}
val mSecondCellList = List(17){TableCell()}
val mThirdCellList = List(17){TableCell()}
val mFourthCellList = List(17){TableCell()}
val mFifthCellList = List(17){TableCell()}
val list = ArrayList<GameRowItem>()
val mDieList = List(6){Die(0); Die(1); Die(2); Die(3); Die(4);
        Die(5)}

class GameActivity : AppCompatActivity(), OnCellClickListener{
    private var mGameRowList = generateRows()
    private var mLoaded: Boolean = false
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f
    private val adapter = GameAdapter(mGameRowList, this)
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerationSensor: Sensor
    private lateinit var dice: List<ImageView>
    private lateinit var mSoundPool: SoundPool
    var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameRecyclerViewBinding.inflate(layoutInflater).also{
            setContentView(it.root)
            it.btnRoll.setOnClickListener { rollDice(); playSound(R.raw.roll_dice) }
            dice = listOf(
                it.ivDieOne, it.ivDieTwo, it.ivDieThree, it.ivDieFour, it.ivDieFive, it.ivDieSix
            )
            //region >>>>>>>>>>>Dice onClickListener
            it.ivDieOne.setOnClickListener{lockUnlockDie(0)}
            it.ivDieTwo.setOnClickListener{lockUnlockDie(1)}
            it.ivDieThree.setOnClickListener{lockUnlockDie(2)}
            it.ivDieFour.setOnClickListener{lockUnlockDie(3)}
            it.ivDieFive.setOnClickListener{lockUnlockDie(4)}
            it.ivDieSix.setOnClickListener{lockUnlockDie(5)}
            //endregion
        }
        //region RecyclerView Table
        binding.rvMainTable
        binding.rvMainTable.adapter = adapter
        binding.rvMainTable.layoutManager = LinearLayoutManager(this)
        binding.rvMainTable.hasFixedSize()
        //endregion
        //region Sound
        this.loadSounds()
        //endregion
        //region Sensor
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //endregion
    }

    //region Sensor
    private val sensorListener = object: SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 12) {
                rollDice()
                playSound(R.raw.roll_dice)
            }
        }
    }

    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(sensorListener, accelerationSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorListener)
    }
    //endregion

    //region >>>>>>>>>>>Sound
    private fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }

        this.mSoundPool.setOnLoadCompleteListener { _, _ , _ -> mLoaded = true}
        this.mSoundMap[R.raw.roll_dice] = this.mSoundPool.load(this, R.raw.roll_dice, 1)
    }

    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
    //endregion

    //region >>>>>>>>>>>Dice
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
    //endregion

    //region >>>>>>>>>>>Table
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
    //endregion

    //region Za klikanje po čelijama
    override fun onSecondCellClick(position: Int) {
        val currentItem = mGameRowList[position]
        currentItem.secondCell.mText = "16"
        adapter.notifyItemChanged(position)
    }

    override fun onThirdCellClick(position: Int) {
        val currentItem = mGameRowList[position]
        currentItem.thirdCell.mText = "35"
        adapter.notifyItemChanged(position)
    }

    override fun onFourthCellClick(position: Int) {
        val currentItem = mGameRowList[position]
        mFourthCellList[position].mText = "22"
        adapter.notifyItemChanged(position)
    }

    override fun onFifthCellClick(position: Int) {
        val currentItem = mGameRowList[position]
        mFifthCellList[position].mText = "5"
        adapter.notifyItemChanged(position)
    }
    //endregion
}