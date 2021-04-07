package com.nether.idz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nether.idz.R
import com.nether.idz.entity.Save

class MainActivity : AppCompatActivity() {
    private lateinit var btnMaterial : Button
    private lateinit var btnTasks : Button
    private lateinit var btnResults : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Save.init()

        btnMaterial = findViewById(R.id.btnMaterial)
        btnTasks = findViewById(R.id.btnTasks)
        btnResults = findViewById(R.id.btnResults)
        addListeners()
    }

    fun addListeners() {
        btnMaterial.setOnClickListener {
            val intent = Intent(this, MaterialsActivity::class.java)
            startActivity(intent)
        }

        btnTasks.setOnClickListener {

        }

        btnResults.setOnClickListener {

        }
    }
}