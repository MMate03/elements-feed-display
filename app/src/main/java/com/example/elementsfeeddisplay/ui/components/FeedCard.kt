package com.example.elementsfeeddisplay.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import com.example.elementsfeeddisplay.domain.model.FeedItem

@Composable
fun ProductCard(item: FeedItem.ProductFeed) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC8E6C9)

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )

    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.text,
                maxLines = 4,
                overflow =  TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.timeStamp,
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
        }

    }


}