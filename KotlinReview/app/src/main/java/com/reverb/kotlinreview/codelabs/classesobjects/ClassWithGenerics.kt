package com.reverb.kotlinreview.codelabs.classesobjects
/**
class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)

class TrueOrFalseQuestion(
    val questionText:String,
    val answer: Boolean,
    val difficulty: String
)

class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)

 * The only difference with classes above is the data type of answer ↑
 *
**/
enum class Difficulty{
    EASY, MEDIUM, HARD
}

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
){
//    companion object{
//        const val TEST_TIME = 30
//    }
}
//singleton
object StudentProgress{
    val total = 10
    var answered = 3
}

class Quiz{
    val question1 = Question<String>("Quoth the raven ___",
        "nevermore", Difficulty.MEDIUM)

    val question2 = Question<Boolean>("The sky is green. True or False",
        false, Difficulty.EASY
    )
    val question3 = Question<Int>("How many days are there between full moons?",
        28, Difficulty.HARD)

    companion object QuizStudentProgress{
        val total = 15
        var answered = 7
    }
}

fun main(){
    println(Quiz().question1.questionText)
    println(Quiz.QuizStudentProgress.total) //with companion keyword, the name QuizStudentProgress is optional
    println("John has answered ${Quiz.answered} of ${Quiz.total} question of the test")
}

/*
fun main() {

    val question1 = Question<String>("Quoth the raven ___",
        "nevermore", Difficulty.MEDIUM)

    val question2 = Question<Boolean>("The sky is green. True or False",
        false, Difficulty.EASY
    )
    val question3 = Question<Int>("How many days are there between full moons?",
        28, Difficulty.HARD)

    //print(question1.toString())//kotlin automatically created toString() for data classes
    //println("The test is ${Question.Companion.TEST_TIME} minutes long")
    println("John has answered ${StudentProgress.answered} of ${StudentProgress.total} question of the test")
}
*/