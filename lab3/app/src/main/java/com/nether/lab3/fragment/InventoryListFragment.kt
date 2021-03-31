package com.nether.lab3.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.nether.lab3.R
import com.nether.lab3.activity.InventoryActivity
import com.nether.lab3.adapter.InventoryAdapter
import com.nether.lab3.entity.Inventory
import java.util.*
import kotlin.collections.ArrayList


class InventoryListFragment : Fragment() {
    private lateinit var inventories: ArrayList<Inventory>
    private lateinit var adapter: InventoryAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inventories = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // зв’язуємо з відповідним макетом
        val view: View =
            inflater.inflate(R.layout.fragment_inventory_list, container, false)
        val listInventories =
            view.findViewById<View>(R.id.list_inventory) as ListView

        // для формування структури списку використовуємо відповідний адаптер (див.нижче п.9)
        adapter = InventoryAdapter(this.context, inventories)

        listInventories.adapter = adapter
        listInventories.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    val intent = Intent(activity, InventoryActivity::class.java)
                    val bundle = Bundle()

                    bundle.putParcelable("inventory", inventories[position])
                    intent.putExtra("b", bundle)
                    intent.putExtra("index", position)

                    startActivityForResult(intent, 0)
                }

        val fab = activity?.findViewById(R.id.fab) as View
        fab.setOnClickListener {
            val intent = Intent(activity, InventoryActivity::class.java)
            startActivityForResult(intent, 0)
        }
        return view
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val index = if (data?.getIntExtra("index", -1)!! > -1) {
                data.getIntExtra("index", -1)
            } else {
                -1
            }
            if (index != -1) {
                when (data.getStringExtra("action")) {
                    "update" -> {
                        val inventory = Inventory()
                        inventory.mTitle = data.getStringExtra("title")
                        inventory.mDate = (data.getSerializableExtra("date") as Date?)!!
                        inventory.mSolved = data.getBooleanExtra("status", false)
                        inventories[index] = inventory
                    }
                    "delete" -> {
                        inventories.removeAt(index)
                    }
                }
            } else {
                val inventory = Inventory()
                inventory.mTitle = data.getStringExtra("title")
                inventory.mDate = (data.getSerializableExtra("date") as Date?)!!
                inventory.mSolved = data.getBooleanExtra("status", false)
                inventories.add(inventory)
            }

            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(activity, "Wrong result", Toast.LENGTH_SHORT).show()
        }
    }
}
