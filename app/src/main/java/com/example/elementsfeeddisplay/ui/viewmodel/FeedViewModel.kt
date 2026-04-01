package com.example.elementsfeeddisplay.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elementsfeeddisplay.domain.model.FeedItem
import com.example.elementsfeeddisplay.domain.repository.IFeedRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FeedViewModel(private val repository: IFeedRepository) : ViewModel() {

    private val _items = mutableStateListOf<FeedItem>()
    val items: List<FeedItem> = _items

    private val pendingBuffer = mutableListOf<FeedItem.ProductFeed>()

    private var fetchJob: Job? = null
    private var isPaused = false
    private var skipCount = 0

    fun onCommandEntered(input: String) {
        if (input.isBlank()) return

        val timestamp = getCurrentTimestamp()
        _items.add(FeedItem.CommandEntry(input.trim(), timestamp))
        when (input.trim().lowercase()) {
            "start" -> startFetching()
            "stop" -> stopFetching()
            "pause" -> isPaused = true
            "resume" -> resumeFetching()

        }

    }

    private fun startFetching(){
        if (fetchJob?.isActive == true) return

        fetchJob = viewModelScope.launch {
            while(isActive){
                val newFeed = repository.getNextProduct(skipCount)
                if(newFeed != null) {
                    if (isPaused) {
                        pendingBuffer.add(newFeed)
                    } else {
                        _items.add(newFeed)
                    }
                    skipCount++
                }
                delay(5000)

            }
        }
    }

    private fun resumeFetching(){
        isPaused = false
        if(pendingBuffer.isNotEmpty()){
            _items.addAll(pendingBuffer)
            pendingBuffer.clear()

        }
    }

    private fun stopFetching() {
        fetchJob?.cancel()
        isPaused = false
        pendingBuffer.clear();
    }

    private fun getCurrentTimestamp(): String {
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    }

    override fun onCleared() {
        super.onCleared()
        stopFetching()
    }
}