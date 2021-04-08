package com.nether.idz.entity

// Single object
object Save {
    lateinit var materials: ArrayList<Material>
    lateinit var tasks: ArrayList<Task>

    fun init() {
        materials = Material.get()
        tasks = Task.get()
    }

    fun getTestMark(index: Int) : Double {
        val test: Test = materials[index].test!!
        return (test.right * 1.0 / test.amount) * test.total
    }

    fun getTestMaxMark(index: Int) : Int {
        return materials[index].test?.total!!
    }

    fun getPassedTestAmount() : Int{
        var count = 0

        for (i : Int in 0 until materials.size) {
            if (materials[i].test?.done!!) {
                count++
            }
        }

        return count
    }

    fun getTaskMark(index: Int) : Double {
        val test: Task = tasks[index]
        return (test.right * 1.0 / test.amount) * test.total
    }

    fun getTaskMaxMark(index: Int) : Int {
        return tasks[index].total
    }

    fun getPassedTaskAmount() : Int {
        var count = 0

        for (i : Int in 0 until tasks.size) {
            if (tasks[i].userAnswer != null) {
                count++
            }
        }

        return count
    }

    fun getTotal() : Int {
        var total = 0

        for (m : Material in materials) {
            total += m.test?.total!!
        }

        for (t : Task in tasks) {
            total += t.total
        }

        return total
    }

    fun getUserTotal() : Double {
        var total = 0.0

        for (i : Int in 0 until materials.size) {
            if (materials[i].test?.done!!) {
                total += getTestMark(i)
            }
        }

        for (i : Int in 0 until tasks.size) {
            if (tasks[i].userAnswer != null) {
                total += getTaskMark(i)
            }
        }

        return total
    }
}