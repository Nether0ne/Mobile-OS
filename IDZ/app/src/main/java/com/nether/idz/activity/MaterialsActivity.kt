package com.nether.idz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import com.nether.idz.R
import com.nether.idz.fragment.MaterialListFragment

class MaterialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = MaterialListFragment()
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_back) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}