package com.example.yambprojekt.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.yambprojekt.R

class InfoDialogFragment: DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_info_dialog, container, false)

        rootView.findViewById<Button>(R.id.btn_return).setOnClickListener{
            dismiss()
        }

        return rootView
    }
}