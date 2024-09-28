package com.example.pdpe_feedback1

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

object NovelaRepository {
    private val novelasPorUsuario: SnapshotStateMap<String, MutableList<Novela>> = mutableStateMapOf()

    fun getNovelas(usuario: String): List<Novela> = novelasPorUsuario[usuario] ?: emptyList()

    fun addNovela(usuario: String, novela: Novela) {
        if (novelasPorUsuario[usuario] == null) {
            novelasPorUsuario[usuario] = mutableListOf()
        }
        novelasPorUsuario[usuario]?.add(novela)
    }

    fun deleteNovela(usuario: String, novela: Novela) {
        novelasPorUsuario[usuario]?.remove(novela)
    }

    fun updateNovela(usuario: String, updatedNovela: Novela) {
        val index = novelasPorUsuario[usuario]?.indexOfFirst { it.titulo == updatedNovela.titulo }
        if (index != null && index != -1) {
            novelasPorUsuario[usuario]?.set(index, updatedNovela)
        }
    }
}