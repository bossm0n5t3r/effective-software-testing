package me.bossm0n5t3r.chapter05

class PassingGrade {
    fun passed(grade: Float): Boolean {
        require(grade in 1.0f..10.0f) { "Grade must be between 1.0 and 10.0 - grade: $grade" }
        return grade >= 5.0f
    }
}
