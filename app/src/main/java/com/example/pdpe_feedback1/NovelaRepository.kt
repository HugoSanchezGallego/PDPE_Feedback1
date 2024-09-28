// NovelaRepository.kt
package com.example.pdpe_feedback1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object NovelaRepository {
    private val novelas: SnapshotStateList<Novela> = mutableStateListOf()

    fun getNovelas(): List<Novela> = novelas

    fun addNovela(novela: Novela) {
        novelas.add(novela)
    }

    fun deleteNovela(novela: Novela) {
        novelas.remove(novela)
    }

    fun updateNovela(updatedNovela: Novela) {
        val index = novelas.indexOfFirst { it.titulo == updatedNovela.titulo }
        if (index != -1) {
            novelas[index] = updatedNovela
        }
    }
}