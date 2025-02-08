package com.suvilao.pixcoin.helpers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.suvilao.pixcoin.responses.Machine
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "machine_prefs")

object MachineStorage {
    private val MACHINE_KEY = stringPreferencesKey("machine_data")

    suspend fun saveMachine(context: Context, machine: Machine) {
        val json = Gson().toJson(machine)
        context.dataStore.edit { preferences ->
            preferences[MACHINE_KEY] = json
        }
    }

    suspend fun getMachine(context: Context): Machine? {
        val preferences = context.dataStore.data.first()
        val json = preferences[MACHINE_KEY] ?: return null
        return Gson().fromJson(json, Machine::class.java)
    }
}
