package com.nether.lab3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.nether.lab3.R
import com.nether.lab3.entity.Inventory


class InventoryAdapter(private val context: Context?, private val inventories: ArrayList<Inventory>)
    : BaseAdapter() {
    override fun getCount(): Int {
        return inventories.size
    }

    override fun getItem(position: Int): Any {
        return inventories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cView = convertView
        cView = LayoutInflater.from(context).inflate(R.layout.listview_layout_inventory, parent, false)

        val currentInventory = getItem(position) as Inventory?

        val textViewInventoryTitle = cView.findViewById<View>(R.id.inventory_title) as TextView
        textViewInventoryTitle.text = currentInventory?.mTitle

        val textViewInventoryDate = cView.findViewById<View>(R.id.inventory_date) as TextView
        textViewInventoryDate.text = currentInventory?.mDate.toString()

        val checkBoxInventoryStatus = cView.findViewById<View>(R.id.inventory_solved) as CheckBox
        checkBoxInventoryStatus.isChecked = currentInventory?.mSolved!!

        textViewInventoryTitle.focusable = View.NOT_FOCUSABLE
        textViewInventoryTitle.isFocusableInTouchMode = false
        textViewInventoryTitle.isClickable = false

        textViewInventoryDate.focusable = View.NOT_FOCUSABLE
        textViewInventoryDate.isFocusableInTouchMode = false
        textViewInventoryDate.isClickable = false

        checkBoxInventoryStatus.focusable = View.NOT_FOCUSABLE
        checkBoxInventoryStatus.isFocusableInTouchMode = false
        checkBoxInventoryStatus.isClickable = false

        return cView
    }

}
