package com.androidui.bottomsheettips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.androidui.bottomsheettips.bottomsheetfragment.BottomSheetFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*get the button*/
        val showButton= findViewById<Button>(R.id.show_modal_sheet)

        showButton.setOnClickListener {
//            val modalView: View = layoutInflater.inflate(R.layout.bottom_sheet_modal, null)
//            val dialog = BottomSheetDialog(this)
//            dialog.setContentView(modalView)
//            dialog.show()

            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}