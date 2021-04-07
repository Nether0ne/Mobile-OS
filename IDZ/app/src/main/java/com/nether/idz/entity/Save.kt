package com.nether.idz.entity

// Single object
object Save {
    lateinit var materials: ArrayList<Material>

    fun init() {
        materials = Material.get()
    }

    fun getTestGrade(index: Int) : Double {
        val test: Test = materials[index].test!!
        return (test.right * 1.0 / test.amount) * test.total
    }

    fun getTaskGrade(index: Int) : Double {
        return 0.0
    }
}