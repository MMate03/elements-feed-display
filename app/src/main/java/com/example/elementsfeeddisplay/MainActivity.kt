package com.example.elementsfeeddisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elementsfeeddisplay.domain.repository.FeedRepository
import com.example.elementsfeeddisplay.ui.FeedScreen
import com.example.elementsfeeddisplay.ui.theme.ElementsFeedDisplayTheme
import com.example.elementsfeeddisplay.ui.viewmodel.FeedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = FeedRepository()

        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FeedViewModel(repository) as T

            }
        }

        val viewModel = ViewModelProvider(this, viewModelFactory)[FeedViewModel::class.java]

        setContent {

                FeedScreen(viewModel = viewModel)

        }
    }
}

