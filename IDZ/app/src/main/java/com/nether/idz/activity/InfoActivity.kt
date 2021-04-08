package com.nether.idz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nether.idz.R
import com.nether.idz.entity.Save
import com.nether.idz.fragment.InfoFragment

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = InfoFragment(Save.materials, Save.tasks)
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}