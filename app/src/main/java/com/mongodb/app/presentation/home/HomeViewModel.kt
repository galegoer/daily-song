package com.mongodb.app.presentation.home

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.mongodb.app.data.SyncRepository
import com.mongodb.app.domain.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

object SongViewEvent

class HomeViewModel constructor(
    private val repository: SyncRepository,
    var currSongState: Song = Song("temp_title","temp_artist")
//    var currSongState: Flow<SingleQueryChange<Item>> = flowOf()
) : ViewModel() {

    private val _event: MutableSharedFlow<SongViewEvent> = MutableSharedFlow()
    val event: Flow<SongViewEvent>
        get() = _event

    init {
        viewModelScope.launch {
            var dailySong = repository.getDailySong()
            System.err.println("HELLO")
            System.err.println(dailySong)
            currSongState = dailySong
        }
    }

    companion object {
        fun factory(
            repository: SyncRepository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null
        ): AbstractSavedStateViewModelFactory {
            return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return HomeViewModel(repository) as T
                }
            }
        }
    }
}
