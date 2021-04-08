package com.nether.idz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nether.idz.R
import com.nether.idz.entity.Save
import com.nether.idz.entity.Task

class OpenTaskFragment(private var task: Task? = null, private var index: Int = -1)
    : Fragment() {
    private lateinit var title : TextView
    private lateinit var description: TextView
    private lateinit var input: EditText
    private lateinit var btnSend: Button

    constructor(bundle: Bundle?) : this()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_open_task, container, false)

        title = view.findViewById(R.id.textTitle)
        title.text = this.resources.getText(task?.title!!)

        description = view.findViewById(R.id.textDescription)
        description.text = this.resources.getText(task?.description!!)

        input = view.findViewById(R.id.textInput)

        btnSend = view.findViewById(R.id.btnSend)

        if (task?.userAnswer != null) {
            btnSend.text = this.resources.getText(R.string.task_passed)
            input.setText(task?.userAnswer)
            input.isFocusable = false
            input.isEnabled = false
            btnSend.isEnabled = false
        } else {
            btnSend.setOnClickListener {
                task?.userAnswer = input.text.toString()
                task?.calcMark(context!!)
                Save.tasks[index] = task!!

                val intent = Intent()
                activity?.setResult(Activity.RESULT_OK, intent)
                activity?.finish()
                activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                Toast.makeText(context, "Задание сдано! Оценка: " + Save.getTaskMark(index), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onStop() {
        super.onStop()

        val intent = Intent()
        activity?.setResult(Activity.RESULT_OK, intent)
    }
}