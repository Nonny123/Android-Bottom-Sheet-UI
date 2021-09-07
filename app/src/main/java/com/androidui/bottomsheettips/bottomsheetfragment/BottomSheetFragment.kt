package com.androidui.bottomsheet

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

        /*initiazile views*/
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
            layoutTutorialIndicator!!.addView(indicators[i])
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentTutorialIndicators(index: Int) {
        val childCount = layoutTutorialIndicator!!.childCount
        for (i in 0 until childCount) {
            val imageView: ImageView = layoutTutorialIndicator!!.getChildAt(i) as ImageView
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
            buttonTutorialAction!!.text = "Start"
        } else {
            buttonTutorialAction!!.text = "Next"
        }
    }

    private fun setTutorialItem() {

        val tutorialItems: MutableList<TutorialItem> = ArrayList()
        val itemFast = TutorialItem(R.drawable.on_the_way,"Fast Delivery to your home", "Our delivery partner is on the way to your home!")
        val itemPay = TutorialItem(R.drawable.pay_online,"Fast Delivery to your home", "Our delivery partner is on the way to your home!")
        val itemEat = TutorialItem(R.drawable.eat_together,"Fast Delivery to your home", "Our delivery partner is on the way to your home!")

        tutorialItems.add(itemFast)
        tutorialItems.add(itemPay)
        tutorialItems.add(itemEat)
        tutorialAdapter = TutorialAdapter(tutorialItems)
    }
}