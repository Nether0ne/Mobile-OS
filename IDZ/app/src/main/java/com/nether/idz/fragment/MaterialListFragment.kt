package com.nether.idz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.activity.MaterialActivity
import com.nether.idz.adapter.MaterialAdapter
import com.nether.idz.entity.Material
import com.nether.idz.entity.Save
import com.nether.idz.entity.Test

class MaterialListFragment : Fragment() {
    private lateinit var materials : ArrayList<Material>
    private lateinit var adapter: MaterialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materials = Save.materials
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)
        val listOfMaterial = view.findViewById<ListView>(R.id.list)

        adapter = MaterialAdapter(this.context, materials)
        listOfMaterial.adapter = adapter

        listOfMaterial.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(activity, MaterialActivity::class.java)
                val bundle = Bundle()

                bundle.putParcelable("material", materials[position])
                intent.putExtra("b", bundle)
                intent.putExtra("index", position)

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