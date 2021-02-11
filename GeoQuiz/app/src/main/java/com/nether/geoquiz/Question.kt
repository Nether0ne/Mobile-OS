package com.nether.geoquiz

data class Question(val mTextResId: Int,
                    val mAnswerTrue: Boolean) {
    companion object {
        fun get(length : Int) : ArrayList<Question> {
            val allQuestions = arrayListOf(
                    Question(R.string.question_1, false),
                    Question(R.string.question_2, true),
                    Question(R.string.question_3, false),
                    Question(R.string.question_4, false),
                    Question(R.string.question_5, true),
                    Question(R.string.question_6, true),
                    Question(R.string.question_7, false),
                    Question(R.string.question_8, false),
                    Question(R.string.question_9, false),
                    Question(R.string.question_10, false)
            )

            var selectedQuestions = arrayListOf<Question>()

            for (i : Int in 0..length) {
                val rnd = (0..allQuestions.size).random()
                selectedQuestions.add(allQuestions.get(rnd))
                allQuestions.removeAt(rnd)
            }

            return selectedQuestions
        }
    }
}
