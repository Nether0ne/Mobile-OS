package com.nether.idz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.nether.idz.R
import com.nether.idz.entity.Material
import com.nether.idz.fragment.MaterialListFragment
import com.nether.idz.fragment.OpenMaterialFragment

class MaterialActivity : AppCompatActivity() {
    private lateinit var material : Material
    private var index : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val intent = this.intent
        index = intent.getIntExtra("index", -1)

        val bundle = if (intent.getParcelableExtra<Bundle>("b") != null) {
            intent.getParcelableExtra<Bundle>("b")
        } else {
            null
        }

        if (bundle != null) {
            material = bundle.getParcelable("material")!!
        } else {
            super.onBackPressed()
            finish()
        }


        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = OpenMaterialFragment(material, index)
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