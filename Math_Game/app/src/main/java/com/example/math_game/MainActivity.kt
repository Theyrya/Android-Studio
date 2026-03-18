package com.example.math_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathGameApp()
        }
    }
}

@Composable
fun MathGameApp() {
    var currentScreen by remember { mutableStateOf("start") }
    var numQuestions by remember { mutableStateOf(5) }
    var questionIndex by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }
    var wrongAnswers by remember { mutableStateOf(0) }
    var questions by remember { mutableStateOf(listOf<Pair<Int, Int>>()) }

    when (currentScreen) {
        "start" -> StartScreen(
            onStart = { count ->
                numQuestions = count
                questions = List(numQuestions) {
                    Random.nextInt(1, 20) to Random.nextInt(1, 20)
                }
                questionIndex = 0
                correctAnswers = 0
                wrongAnswers = 0
                currentScreen = "question"
            }
        )
        "question" -> QuestionScreen(
            question = questions[questionIndex],
            questionNumber = questionIndex + 1,
            totalQuestions = numQuestions,
            correctCount = correctAnswers,
            wrongCount = wrongAnswers,
            onNext = { answer ->
                val (a, b) = questions[questionIndex]
                if (answer == a + b) correctAnswers++ else wrongAnswers++
                if (questionIndex + 1 < numQuestions) {
                    questionIndex++
                } else {
                    currentScreen = "result"
                }
            },
            onCancel = { currentScreen = "start" }
        )
        "result" -> ResultScreen(
            correct = correctAnswers,
            wrong = wrongAnswers,
            total = numQuestions,
            onRestart = { currentScreen = "start" }
        )
    }
}

@Composable
fun StartScreen(onStart: (Int) -> Unit) {
    var input by remember { mutableStateOf("5") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Math Game",
                fontSize = 36.sp,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { input = it.filter { c -> c.isDigit() } },
                label = { Text("Number of Questions") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val count = input.toIntOrNull() ?: 5
                    onStart(count)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(150.dp)
            ) {
                Text("Start")
            }
        }
    }
}

@Composable
fun QuestionScreen(
    question: Pair<Int, Int>,
    questionNumber: Int,
    totalQuestions: Int,
    correctCount: Int,
    wrongCount: Int,
    onNext: (Int) -> Unit,
    onCancel: () -> Unit
) {
    var answerInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Score Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Correct: $correctCount", color = Color.Green, fontSize = 18.sp)
            Text("Wrong: $wrongCount", color = Color.Red, fontSize = 18.sp)
        }

        // Question Box
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Question $questionNumber / $totalQuestions",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "${question.first} + ${question.second} = ?",
                fontSize = 32.sp,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = answerInput,
                onValueChange = { answerInput = it.filter { c -> c.isDigit() } },
                label = { Text("Your Answer") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val ans = answerInput.toIntOrNull() ?: -1
                    onNext(ans)
                    answerInput = ""
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(
                onClick = onCancel,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun ResultScreen(correct: Int, wrong: Int, total: Int, onRestart: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Game Over!", fontSize = 36.sp, color = Color(0xFF6200EE))
            Spacer(modifier = Modifier.height(20.dp))
            Text("Correct: $correct", fontSize = 24.sp, color = Color.Green)
            Text("Wrong: $wrong", fontSize = 24.sp, color = Color.Red)
            Text("Total Questions: $total", fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = onRestart,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Play Again")
            }
        }
    }
}