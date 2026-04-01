package com.example.elementsfeeddisplay.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.elementsfeeddisplay.domain.model.FeedItem
import com.example.elementsfeeddisplay.ui.components.CommandCard
import com.example.elementsfeeddisplay.ui.components.ProductCard
import com.example.elementsfeeddisplay.ui.viewmodel.FeedViewModel
import org.w3c.dom.Text

@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    val listState = rememberLazyListState()
    var textInput by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.items.size) {
        if (viewModel.items.isNotEmpty()) {
            listState.animateScrollToItem(viewModel.items.size - 1)

        }
    }

    Scaffold(
        bottomBar = {
            Surface(tonalElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .imePadding(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextField(
                        value = textInput,
                        onValueChange = {textInput = it},
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Write command(Start/Stop/Pause)")},
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        viewModel.onCommandEntered(textInput)
                        textInput = ""
                    }){
                        Text("Enter")
                    }
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .border(
                    width = 2.dp,
                    color = Color(0xFF2196F3),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(viewModel.items){
                item -> when (item) {
                    is FeedItem.ProductFeed -> ProductCard(item)
                    is FeedItem.CommandEntry -> CommandCard(item)
                }
            }
        }
    }
}

