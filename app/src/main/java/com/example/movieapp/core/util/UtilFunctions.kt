package com.example.movieapp.core.util

import timber.log.Timber

/**
 * ✅ Sênior: Funções Top-Level (soltas no arquivo) dispensam o uso de 'object UtilFunctions'.
 * Agora você pode chamá-las de qualquer lugar do app apenas digitando 'logError(...)' direto.
 */

fun logError(tag: String, message: String) {
    Timber.tag(tag).e("Error -> $message")
}

fun logInfo(tag: String, message: String) {
    // ✅ CORRIGIDO: Alterado de .e() para .i() para classificar o log com o nível correto de severidade
    Timber.tag(tag).i("Info -> $message")
}