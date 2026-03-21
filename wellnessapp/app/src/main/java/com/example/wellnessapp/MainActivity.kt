package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wellnessapp.ui.theme.WellnessAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellnessAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessApp()
                }
            }
        }
    }
}

data class Tip(
    val day: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessApp() {
    val tips = listOf(
        Tip(1, "Start your day without your phone", "Avoid screens for the first 30 minutes after waking up.", R.drawable.day1),
        Tip(2, "Take a mindful walk", "Walk slowly and notice your surroundings.", R.drawable.day2),
        Tip(3, "Drink 2 liters of water", "Track your water intake throughout the day.", R.drawable.day3),
        Tip(4, "Declutter one small area", "Clean your desk or a drawer to feel lighter.", R.drawable.day4),
        Tip(5, "Stretch for 10 minutes", "Loosen up your muscles and improve flexibility.", R.drawable.day5),
        Tip(6, "Write your thoughts", "Journal anything on your mind without overthinking.", R.drawable.day6),
        Tip(7, "Do a digital detox evening", "Avoid social media for the entire evening.", R.drawable.day7),
        Tip(8, "Eat a fruit-only snack", "Replace junk food with fresh fruits.", R.drawable.day8),
        Tip(9, "Learn something new", "Watch a short educational video or read an article.", R.drawable.day9),
        Tip(10, "Wake up early", "Start your day at least 1 hour earlier than usual.", R.drawable.day10),
        Tip(11, "Compliment someone", "Make someone’s day better with kind words.", R.drawable.day11),
        Tip(12, "Go tech-free for 1 hour", "Spend time without any devices.", R.drawable.day12),
        Tip(13, "Practice deep breathing", "Take 10 slow breaths and relax your body.", R.drawable.day13),
        Tip(14, "Organize your tasks", "Make a simple to-do list for the day.", R.drawable.day14),
        Tip(15, "Do a quick workout", "Try a 15-minute home workout.", R.drawable.day15),
        Tip(16, "Listen to calming music", "Relax your mind with soft music.", R.drawable.day16),
        Tip(17, "Spend time in silence", "Sit quietly and observe your thoughts.", R.drawable.day17),
        Tip(18, "Cook a simple meal", "Prepare something healthy at home.", R.drawable.day18),
        Tip(19, "Avoid sugar for a day", "Cut down sweets and sugary drinks.", R.drawable.day19),
        Tip(20, "Help someone", "Do a small act of kindness.", R.drawable.day20),
        Tip(21, "Watch the sunset", "Take a moment to enjoy nature.", R.drawable.day21),
        Tip(22, "Reflect on your goals", "Think about where you are heading in life.", R.drawable.day22),
        Tip(23, "Limit negative thoughts", "Catch and replace negative thinking.", R.drawable.day23),
        Tip(24, "Try a new hobby", "Draw, code, or explore something creative.", R.drawable.day24),
        Tip(25, "Drink herbal tea", "Relax your body with a warm drink.", R.drawable.day25),
        Tip(26, "Take proper breaks", "Rest between work sessions.", R.drawable.day26),
        Tip(27, "Do nothing for 10 minutes", "Just relax without any distractions.", R.drawable.day27),
        Tip(28, "Plan tomorrow", "Prepare your tasks for the next day.", R.drawable.day28),
        Tip(29, "Smile intentionally", "Boost your mood by smiling more.", R.drawable.day29),
        Tip(30, "Celebrate small wins", "Acknowledge your progress this month.", R.drawable.day30)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("30 Days of improvement", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tips) { tip ->
                TipCard(tip)
            }
        }
    }
}

@Composable
fun TipCard(tip: Tip) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Day ${tip.day}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = tip.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Image(
                painter = painterResource(id = tip.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Text(
                text = tip.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}