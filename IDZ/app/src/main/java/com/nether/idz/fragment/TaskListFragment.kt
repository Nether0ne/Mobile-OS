package com.nether.idz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.activity.MaterialActivity
import com.nether.idz.activity.TaskActivity
import com.nether.idz.adapter.TaskAdapter
import com.nether.idz.entity.Save
import com.nether.idz.entity.Task

class TaskListFragment : Fragment() {
    private lateinit var tasks: ArrayList<Task>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasks = Save.tasks
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        val listOfMaterial = view.findViewById<ListView>(R.id.list)

        adapter = TaskAdapter(this.context, tasks)
        listOfMaterial.adapter = adapter

        listOfMaterial.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val intent = Intent(activity, TaskActivity::class.java)
                    val bundle = Bundle()

                    bundle.putParcelable("task", tasks[position])
                    bundle.putInt("index", position)
                    intent.putExtra("b", bundle)

                    startActivityForResult(intent, 0)
                }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged()
        }
    }
}