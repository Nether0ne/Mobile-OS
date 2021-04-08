package com.nether.idz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nether.idz.R
import com.nether.idz.entity.Task
import com.nether.idz.fragment.OpenTaskFragment

class TaskActivity : AppCompatActivity() {
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val intent = this.intent
        var index = -1

        val bundle = if (intent.getParcelableExtra<Bundle>("b") != null) {
            intent.getParcelableExtra<Bundle>("b")
        } else {
            null
        }

        if (bundle != null) {
            task = bundle.getParcelable("task")!!
            index = bundle.getInt("index")
        } else {
            super.onBackPressed()
            finish()
        }


        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = OpenTaskFragment(task, index)
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}