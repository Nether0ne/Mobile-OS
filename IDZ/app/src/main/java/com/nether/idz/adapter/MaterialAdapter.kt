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
import com.nether.idz.entity.Material
import com.nether.idz.entity.Save
import kotlin.collections.ArrayList

class MaterialAdapter(private val context: Context?, private val items: ArrayList<Material>)
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
        cView = LayoutInflater.from(context).inflate(R.layout.fragment_material, parent, false)

        val currentMaterial = getItem(position) as Material?

        val materialTitle = cView.findViewById<View>(R.id.taskTitle) as TextView
        materialTitle.text = this.context?.resources?.getText(currentMaterial?.title!!)

        val statusCheckBox = cView.findViewById<View>(R.id.statusCheckBox) as CheckBox
        statusCheckBox.isChecked = currentMaterial?.test?.done!!
        statusCheckBox.text = this.context?.resources?.getText(R.string.status)
        statusCheckBox.isEnabled = false

        val mark = cView.findViewById<View>(R.id.textMark) as TextView
        if (currentMaterial.test?.done!!) {
            mark.text = Save.getTestMark(position).toString() + " / " + Save.getTestMaxMark(position)
        } else {
            mark.isVisible = false
        }

        return cView
    }
}
