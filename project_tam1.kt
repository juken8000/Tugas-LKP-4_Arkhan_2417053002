package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Scaffold { padding ->
                    MainScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F172A),
                        Color(0xFF020617)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {

        HeaderSection()

        Spacer(modifier = Modifier.height(20.dp))

        StatsSection()

        Spacer(modifier = Modifier.height(25.dp))

        TaskSection()

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(24.dp)) {

        Text(
            text = "FocusFlow",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Stay productive, stay sharp",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF22C55E)
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Text("Today's Progress", color = Color.White)

                Text(
                    "Focus Running",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = 0.6f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    color = Color.White,
                    trackColor = Color(0xFF4ADE80)
                )
            }
        }
    }
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatCard("Done", "1")
        StatCard("Pending", "3")
        StatCard("Focus", "2h")
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, color = Color.White, fontWeight = FontWeight.Bold)
            Text(title, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

data class Task(
    val title: String,
    val duration: String,
    val image: Int,
    val status: String
)

@Composable
fun TaskSection() {

    var completed by remember { mutableStateOf(0) }

    val tasks = listOf(
        Task("UI Design", "120 menit", R.drawable.task_ui, "In Progress"),
        Task("Analisis Sistem", "90 menit", R.drawable.task_analisis, "Pending"),
        Task("Ngoding", "150 menit", R.drawable.task, "In Progress"),
        Task("Membaca", "60 menit", R.drawable.task_read, "Done")
    )

    Column {

        Text(
            text = "Today Focus",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tasks) { task ->

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E293B)
                    ),
                    modifier = Modifier.width(180.dp)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Image(
                            painter = painterResource(id = task.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = task.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = task.duration,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "🔥 ${task.status}",
                            color = Color(0xFF22C55E),
                            fontSize = 11.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                completed++
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Start Task")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Task Completed: $completed",
            color = Color.White,
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}