package com.nether.idz.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.activity.MaterialActivity
import com.nether.idz.adapter.MaterialAdapter
import com.nether.idz.adapter.TaskAdapter
import com.nether.idz.entity.Material
import com.nether.idz.entity.Save
import com.nether.idz.entity.Task

class InfoFragment(
        private val materials : ArrayList<Material>,
        private val tasks : ArrayList<Task>
) : Fragment() {
    private lateinit var totalText : TextView
    private lateinit var textMaterialsAmount : TextView
    private lateinit var textTasksAmount : TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_info, container, false)
        val listOfMaterial = view.findViewById<ListView>(R.id.list_materials)

        val mAdapter = MaterialAdapter(this.context, materials)
        listOfMaterial.adapter = mAdapter

        val listOfTasks = view.findViewById<ListView>(R.id.list_tasks)

        val tAdapter = TaskAdapter(this.context, tasks)
        listOfTasks.adapter = tAdapter

        val total = Save.getTotal()
        val userTotal = Save.getUserTotal()

        totalText = view.findViewById(R.id.textTotal)
        totalText.text = "Набрано баллов: $userTotal / $total"

        textMaterialsAmount = view.findViewById(R.id.textMaterialsAmount)
        textMaterialsAmount.text = "Пройдено тестов: " + Save.getPassedTestAmount() + " / " + Save.materials.size

        textTasksAmount = view.findViewById(R.id.textTasksAmount)
        textTasksAmount.text = "Пройдено заданий: " + Save.getPassedTaskAmount() + " / " + Save.tasks.size

        return view
    }
}