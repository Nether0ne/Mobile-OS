package com.nether.lab3.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nether.lab3.R
import com.nether.lab3.entity.Inventory
import com.nether.lab3.fragment.InventoryFragment


class InventoryActivity : AppCompatActivity() {
    private lateinit var inventory: Inventory
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val intent = this.intent
        index = intent.getIntExtra("index", -1)

        val bundle = if (intent.getParcelableExtra<Bundle>("b") != null) {
            intent.getParcelableExtra<Bundle>("b")
        } else {
            null
        }

        inventory = Inventory()

        val fragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = InventoryFragment(bundle)
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
            return true
        }

        if (item.itemId == R.id.action_save) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as InventoryFragment?

            if (fragment != null) {
                inventory = fragment.mInventory!!
            }

            val intent = Intent()
            intent.putExtra("action", "update")
            intent.putExtra("title", inventory.mTitle)
            intent.putExtra("id", inventory.mId)
            intent.putExtra("date", inventory.mDate)
            intent.putExtra("status", inventory.mSolved)
            intent.putExtra("index", index)

            setResult(Activity.RESULT_OK, intent)
            finish()
            super.onBackPressed()
        } else if (item.itemId == R.id.action_delete) {
            intent.putExtra("action", "delete")
            intent.putExtra("index", index)

            setResult(Activity.RESULT_OK, intent)
            finish()
            super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}
