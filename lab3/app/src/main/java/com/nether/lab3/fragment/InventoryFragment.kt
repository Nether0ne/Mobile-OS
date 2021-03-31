package com.nether.lab3.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.nether.lab3.R
import com.nether.lab3.entity.Inventory


class InventoryFragment() : Fragment() {
    var mInventory: Inventory? = null
    lateinit var mTitleField: EditText
    lateinit var mDateButton: Button
    lateinit var mSolvedCheckBox: CheckBox

    constructor(bundle: Bundle?) : this() {
        if (bundle != null) {
            mInventory = bundle.getParcelable("inventory")!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mInventory == null) {
            mInventory = Inventory()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.listview_layout_inventory, container, false)
        mTitleField = view.findViewById(R.id.inventory_title)
        mTitleField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // empty
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                mInventory?.mTitle = s.toString()
            }

            override fun afterTextChanged(s: Editable) {
                // empty
            }
        })

        mDateButton = view.findViewById(R.id.inventory_date) as Button
        mDateButton.text = mInventory?.mDate.toString()
        mDateButton.isEnabled = false

        mSolvedCheckBox = view.findViewById(R.id.inventory_solved)
        mSolvedCheckBox.setOnCheckedChangeListener { buttonView, isChecked -> mInventory?.mSolved = isChecked }

        if (mInventory != null) {
            mTitleField.setText(mInventory?.mTitle)
            mDateButton.text = mInventory?.mDate.toString()
            mSolvedCheckBox.isChecked = mInventory?.mSolved!!
        }

        return view
    }
}