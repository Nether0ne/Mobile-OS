package com.nether.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class AddStudentActivity : AppCompatActivity() {
    private var index = 0
    private var student: Student? = null
    private var studentTitleName: TextInputEditText? = null
    private var studentTitleClass: TextInputEditText? = null
    private var studentTitleSize: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        val intent = this.intent
        index = intent.getIntExtra("index", -1)
        student = intent?.getParcelableExtra<Parcelable>("student") as Student?

        studentTitleName = findViewById(R.id.student_title_name)
        studentTitleName?.setText(student?.name)
        studentTitleClass = findViewById(R.id.student_title_phone)

        studentTitleClass?.setText(student?.phone)
        studentTitleSize = findViewById(R.id.student_title_skipped)
        studentTitleSize?.setText(student?.skipped)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_student, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(itemMenu: MenuItem): Boolean {
        if (itemMenu.itemId == R.id.home) {
            finish()
            super.onBackPressed()
            return true
        }
        if (itemMenu.getItemId() == R.id.action_save) {
            var student = Student()
            student.name = findViewById<TextInputEditText>(R.id.student_title_name).text.toString()
            student.phone = findViewById<TextInputEditText>(R.id.student_title_phone).text.toString()
            student.skipped = findViewById<TextInputEditText>(R.id.student_title_skipped).text.toString()
            val intent = Intent()
            intent.putExtra("index", index)
            intent.putExtra("student", student)
            setResult(Activity.RESULT_OK, intent)
            super.onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(itemMenu)
    }

}