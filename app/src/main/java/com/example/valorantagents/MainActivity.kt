package com.example.valorantagents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.valorantagents.ui.ValorantAgentsApp
import com.example.valorantagents.ui.ValorantAgentsViewModel
import com.example.valorantagents.ui.ViewModelFactory
import com.example.valorantagents.ui.theme.ValorantAgentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel : ValorantAgentsViewModel by viewModels {
            viewModelFactory
        }

        setContent {
            ValorantAgentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ValorantAgentsApp(viewModel = viewModel)
                }
            }
        }
    }
}
