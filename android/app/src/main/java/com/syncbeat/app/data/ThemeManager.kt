package com.syncbeat.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.syncbeat.app.ui.theme.AppThemeColors
import com.syncbeat.app.ui.theme.AppThemes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "syncbeat_settings")

class ThemeManager(private val context: Context) {
    companion object {
        private val THEME_ID = stringPreferencesKey("theme_id")
    }

    val themeIdFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[THEME_ID] ?: AppThemes.NeumorphismLight.id
    }

    val themeFlow: Flow<AppThemeColors> = themeIdFlow.map { id ->
        AppThemes.byId(id)
    }

    suspend fun setTheme(themeId: String) {
        context.dataStore.edit { prefs ->
            prefs[THEME_ID] = themeId
        }
    }
}
