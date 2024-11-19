package com.teamoffroad.feature.explore.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultLocationPreferencesDataSource @Inject constructor(
    @Named("locationDataStore") private val dataStore: DataStore<Preferences>,
) : LocationPreferencesDataSource {

    object PreferencesKey {
        val LATITUDE_KEY = doublePreferencesKey("LATITUDE_KEY")
        val LONGITUDE_KEY = doublePreferencesKey("LONGITUDE_KEY")
    }

    override val location: Flow<Pair<Double, Double>> = dataStore.data.map { preferences ->
        val latitude = preferences[PreferencesKey.LATITUDE_KEY] ?: DEFAULT_LOCATION_LATITUDE
        val longitude = preferences[PreferencesKey.LONGITUDE_KEY] ?: DEFAULT_LOCATION_LONGITUDE
        latitude to longitude
    }

    override suspend fun setLocation(latitude: Double, longitude: Double) {
        if (latitude != DEFAULT_LOCATION_LATITUDE || longitude != DEFAULT_LOCATION_LONGITUDE) {
            dataStore.edit { preferences ->
                preferences[PreferencesKey.LATITUDE_KEY] = latitude
                preferences[PreferencesKey.LONGITUDE_KEY] = longitude
            }
        }
    }

    companion object {
        private const val DEFAULT_LOCATION_LATITUDE = 37.588764
        private const val DEFAULT_LOCATION_LONGITUDE = 127.05879
    }
}
