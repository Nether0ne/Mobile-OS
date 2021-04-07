package com.nether.idz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.entity.Material

class MaterialFragment(private val material: Material? = null)
    : Fragment() {
    private lateinit var title : TextView
    private lateinit var content: TextView
    private lateinit var done: CheckBox

    constructor(bundle: Bundle?) : this()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_material, container, false)

        title = view.findViewById(R.id.textTitle)
        title.text = this.resources.getText(material?.title!!)
        content = view.findViewById(R.id.textContent)
        content.text = this.resources.getText(material.content!!)
        done = view.findViewById(R.id.statusCheckBox)
        done.isChecked = material.test?.done!!

        return view
    }
}