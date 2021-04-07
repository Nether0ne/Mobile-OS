package com.nether.idz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.activity.TestActivity
import com.nether.idz.entity.Material
import com.nether.idz.entity.Save
import com.nether.idz.entity.Test


class OpenMaterialFragment(private val material: Material? = null, private var index : Int = -1)
    : Fragment() {
    private lateinit var title : TextView
    private lateinit var content: TextView
    private lateinit var btnTest: Button

    constructor(bundle: Bundle?) : this()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_open_material, container, false)

        title = view.findViewById(R.id.textTitle)
        title.text = this.resources.getText(material?.title!!)

        content = view.findViewById(R.id.textContent)
        content.text = this.resources.getText(material.content!!)

        btnTest = view.findViewById(R.id.btnNext)

        if (material.test?.done!!) {
            btnTest.text = this.resources.getText(R.string.test_passed)
            btnTest.isClickable = false
        } else {
            btnTest.setOnClickListener {
                val intent = Intent(activity, TestActivity::class.java)
                val bundle = Bundle()

                bundle.putParcelable("test", material.test)
                intent.putExtra("b", bundle)
                startActivityForResult(intent, 0)
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val t = data?.getParcelableExtra<Test>("test")
            t?.done = true

            material?.test = t
            Save.materials[index].test = t
            // Reload fragment
            activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
            Toast.makeText(context, "Тест завершен! Оценка: " + Save.getTestGrade(index), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Тест завершен до окончания", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()

        val intent = Intent()
        activity?.setResult(Activity.RESULT_OK, intent)
    }
}