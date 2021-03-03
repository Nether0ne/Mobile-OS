package com.nether.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var students : ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList<Student>("list") as ArrayList<Student>
        } else {
            ArrayList()
        }
        
        val liststudent = findViewById<View>(R.id.listStudents) as ListView
        val adapter = StudentAdapter(this, students)
        liststudent.adapter = adapter
        liststudent.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
                intent.putExtra("index", position)
                intent.putExtra("student", students[position])
                startActivityForResult(intent, 0)
            }

        val fab = findViewById<View>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("list", students)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        students = savedInstanceState.getParcelableArrayList<Student>("list") as ArrayList<Student>
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val index = data?.getIntExtra("index", -1)
            val student = data?.getParcelableExtra<Parcelable>("student") as Student?
            if (index != -1) {
                students[index!!] = student!!
            } else {
                students.add(student!!)
                val listView = findViewById<ListView>(R.id.listStudents)
                val adapter = StudentAdapter(this, students)
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show()
        }
    }
}