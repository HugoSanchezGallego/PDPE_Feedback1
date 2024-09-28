package com.example.pdpe_feedback1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object ResenaRepository {
    private val resenas: SnapshotStateList<Resena> = mutableStateListOf()

    fun getAllResenas(): List<Resena> = resenas

    fun addResena(novela: Novela, resena: Resena) {
        resenas.add(resena)
    }
}