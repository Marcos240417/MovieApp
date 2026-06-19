package com.example.movieapp.core.util

/**
 * Representa os estados possíveis de um fluxo de dados na aplicação.
 * Utiliza covariância <out T> para permitir flexibilidade de tipos nos estados.
 */
sealed class ResultData<out T> {

    // ✅ Sênior: Atualizado para 'data object' com inicial maiúscula para debug limpo
    data object Loading : ResultData<Nothing>()

    // ✅ Sênior: Corrigida a grafia de 'Sucsses' para 'Success'
    data class Success<out T>(val data: T) : ResultData<T>()

    // ✅ Sênior: Alterado de Exception para Throwable (Padrão do ecossistema de Coroutines/Retrofit)
    data class Failure(val throwable: Throwable) : ResultData<Nothing>()
}