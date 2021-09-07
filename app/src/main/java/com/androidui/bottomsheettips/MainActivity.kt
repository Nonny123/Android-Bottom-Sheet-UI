package com.androidui.bottomsheettips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showModalSheet = findViewById<Button>(R.id.show_modal_sheet)
        showModalSheet.setOnClickListener {
            val modalView: View = layoutInflater.inflate(R.layout.bottom_sheet_modal, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(modalView)
            dialog.show()
        }
    }
}