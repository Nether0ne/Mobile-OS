package com.nether.idz.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.nether.idz.R
import com.nether.idz.entity.Save
import com.nether.idz.entity.Task

class TaskAdapter(private val context: Context?, private val items: ArrayList<Task>)
    : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var cView = convertView
        cView = LayoutInflater.from(context).inflate(R.layout.fragment_task, parent, false)

        val currentTask = getItem(position) as Task?

        val taskTitle = cView.findViewById<View>(R.id.taskTitle) as TextView
        taskTitle.text = this.context?.resources?.getText(currentTask?.title!!)

        val statusCheckBox = cView.findViewById<View>(R.id.statusCheckBox) as CheckBox
        statusCheckBox.isChecked = currentTask?.userAnswer != null
        statusCheckBox.text = this.context?.resources?.getText(R.string.status)
        statusCheckBox.isEnabled = false

        val mark = cView.findViewById<View>(R.id.textMark) as TextView
        if (currentTask?.userAnswer != null) {
            mark.text = Save.getTaskMark(position).toString() + " / " + Save.getTaskMaxMark(position)
        } else {
            mark.isVisible = false
        }

        return cView
    }
}
