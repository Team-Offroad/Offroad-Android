package com.teamoffroad.feature.explore.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreCameraViewModel @Inject constructor(

) : ViewModel() {

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double, qr: String) {
        // TODO: API 호출
        Log.e("123123", "postExploreResult: $placeId, $latitude, $longitude, $qr")
    }
}
