package com.nether.idz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nether.idz.R
import com.nether.idz.entity.Test
import com.nether.idz.fragment.OpenMaterialFragment
import com.nether.idz.fragment.TestFragment

class TestActivity : AppCompatActivity() {
    private lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val intent = this.intent

        val bundle = if (intent.getParcelableExtra<Bundle>("b") != null) {
            intent.getParcelableExtra<Bundle>("b")
        } else {
            null
        }

        if (bundle != null) {
            test = bundle.getParcelable("test")!!
        } else {
            super.onBackPressed()
            finish()
        }


        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = TestFragment(test)
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}