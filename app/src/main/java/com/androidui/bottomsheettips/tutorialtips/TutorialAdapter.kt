package com.androidui.bottomsheettips.tutorialtips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidui.bottomsheettips.R


class TutorialAdapter(_tutorialItems: List<TutorialItem>): RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder>() {

    private var tutorialItems: List<TutorialItem> = _tutorialItems

//    fun TutorialAdapter(tutorialItems: List<TutorialItem>) {
//        this.tutorialItems = tutorialItems
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        return TutorialViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_tutorial, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        holder.setTutorialData(tutorialItems!![position])
    }

    override fun getItemCount(): Int {
        return tutorialItems!!.size
    }

    class TutorialViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        private val imageTutorial: ImageView = itemView.findViewById(R.id.imageTutorial)

        fun setTutorialData(tutorialItem: TutorialItem) {
            textTitle.text = tutorialItem.title
            textDescription.text = tutorialItem.description
            imageTutorial.setImageResource(tutorialItem.image)
        }

    }
}