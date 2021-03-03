package com.nether.studentapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class StudentAdapter(context: Context, students: ArrayList<Student>) : BaseAdapter() {
    private val context: Context = context
    private val students: ArrayList<Student> = students
    override fun getCount(): Int {
        return students.size
    }

    override fun getItem(position: Int): Any? {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_layout_item, parent, false)
        }
        val currentItem = getItem(position) as Student?
        val textViewItemName = convertView?.findViewById(R.id.student_name) as TextView
        textViewItemName.text = currentItem?.name
        val textViewItemClassification =
            convertView.findViewById(R.id.student_phone) as TextView
        textViewItemClassification.text = currentItem?.phone
        return convertView
    }
}