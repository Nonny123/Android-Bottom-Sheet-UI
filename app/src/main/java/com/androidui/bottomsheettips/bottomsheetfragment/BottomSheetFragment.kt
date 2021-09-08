package com.androidui.bottomsheettips.bottomsheetfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.androidui.bottomsheettips.MainActivity
import com.androidui.bottomsheettips.R
import com.androidui.bottomsheettips.tutorialtips.TutorialAdapter
import com.androidui.bottomsheettips.tutorialtips.TutorialItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.util.*


class BottomSheetFragment() : BottomSheetDialogFragment() {

    /*get views*/
    private lateinit var tutorialAdapter: TutorialAdapter
    private lateinit var layoutTutorialIndicator: LinearLayout
    private lateinit var buttonTutorialAction: MaterialButton

    private var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet_modal, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        /*initialize views*/
        layoutTutorialIndicator = requireView().findViewById(R.id.layoutTutorialIndicators)
        buttonTutorialAction = requireView().findViewById(R.id.buttonTutorialAction)

        /*set dummy data*/
        setTutorialItem()

        val tutorialViewPager = requireView().findViewById<ViewPager2>(R.id.tutorialViewPager)
        tutorialViewPager.adapter = tutorialAdapter

        /*set indicator*/
        setTutorialIndicator()
        setCurrentTutorialIndicators(0)

        /*handle viewchange*/
        tutorialViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentTutorialIndicators(position)
            }
        })

        buttonTutorialAction.setOnClickListener {
            if (tutorialViewPager.currentItem + 1 < tutorialAdapter.getItemCount()) {
                tutorialViewPager.currentItem = tutorialViewPager.currentItem + 1
            } else {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }


    private fun setTutorialIndicator() {
        val indicators: Array<ImageView?> =
            arrayOfNulls(tutorialAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it, R.drawable.tutorial_indicator_inactive
                    )
                }
            )
            indicators[i]?.layoutParams = layoutParams
            layoutTutorialIndicator.addView(indicators[i])
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentTutorialIndicators(index: Int) {
        val childCount = layoutTutorialIndicator.childCount
        for (i in 0 until childCount) {
            val imageView: ImageView = layoutTutorialIndicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.tutorial_indicator_active
                        )
                    }
                )
            } else {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.tutorial_indicator_inactive
                        )
                    }
                )
            }
        }
        if (index == tutorialAdapter.itemCount - 1) {
            buttonTutorialAction.text = "Start"
        } else {
            buttonTutorialAction.text = "Next"
        }
    }

    private fun setTutorialItem() {

        val tutorialItems: MutableList<TutorialItem> = ArrayList()
        val itemOne = TutorialItem(R.drawable.on_the_way,"Keep accurate records", "To keep records, find the best database")
        val itemTwo = TutorialItem(R.drawable.pay_online,"Track sales and records", "Use the products page to  monitor products!")
        val itemThree = TutorialItem(R.drawable.eat_together,"Efficient accounting", "Use the report page to view income and loss")
        val itemFour = TutorialItem(R.drawable.on_the_way,"Get notifications", "To keep records, find the best database")
        val itemFive = TutorialItem(R.drawable.pay_online,"Real time alerts", "Use the products page to  monitor products!")
        val itemSix = TutorialItem(R.drawable.eat_together,"Shop settings", "Use the report page to view income and loss")
        val itemSeven = TutorialItem(R.drawable.on_the_way,"Mall settings", "To keep records, find the best database")
        val itemEight = TutorialItem(R.drawable.pay_online,"Inventory system", "Use the products page to  monitor products!")
        val itemNine = TutorialItem(R.drawable.eat_together,"Multiple shops", "Use the report page to view income and loss")
        val itemTen = TutorialItem(R.drawable.on_the_way,"Add agents", "To keep records, find the best database")
        val itemEleven = TutorialItem(R.drawable.pay_online,"Manage products", "Use the products page to  monitor products!")

        tutorialItems.add(itemOne)
        tutorialItems.add(itemTwo)
        tutorialItems.add(itemThree)
        tutorialItems.add(itemFour)
        tutorialItems.add(itemFive)
        tutorialItems.add(itemSix)
        tutorialItems.add(itemSeven)
        tutorialItems.add(itemEight)
        tutorialItems.add(itemNine)
        tutorialItems.add(itemTen)
        tutorialItems.add(itemEleven)

        tutorialAdapter = TutorialAdapter(tutorialItems)
    }
}