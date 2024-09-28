package com.example.pdpe_feedback1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object ResenaRepository {
    private val resenas: SnapshotStateList<Resena> = mutableStateListOf()

    fun getResenasByTitulo(titulo: String): List<Resena> {
        return resenas.filter { it.novelaTitulo == titulo }
    }

    fun addResena(resena: Resena) {
        resenas.add(resena)
    }
}