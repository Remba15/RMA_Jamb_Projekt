package com.example.yambprojekt.activities

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.yambprojekt.R
import java.io.*


class InfoDialogFragment: DialogFragment(){
    lateinit var mInfoText: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_info_dialog, container, false)

        rootView.findViewById<Button>(R.id.btn_return).setOnClickListener{
            dismiss()
        }

        mInfoText = rootView.findViewById(R.id.info_text)
        mInfoText.movementMethod = ScrollingMovementMethod()
        readTextFile()

        return rootView
    }

    fun readTextFile(){
        val mInputStream: InputStream
        val mOutputString: String

        try{
            mInputStream = this.resources.openRawResource(R.raw.ruleset)
            val size: Int = mInputStream.available()
            val buffer = ByteArray(size)
            mInputStream.read(buffer)
            mOutputString = String(buffer)
            mInfoText.text = mOutputString
        } catch (e: IOException){
            e.printStackTrace()
        }

    }
}