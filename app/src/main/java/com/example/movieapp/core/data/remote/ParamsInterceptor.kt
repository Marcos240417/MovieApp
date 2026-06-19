package com.example.movieapp.core.data.remote

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor responsável por injetar chaves de autenticação e regras de controle.
 * ✅ PADRÃO SÊNIOR: Configurado com Cache-Control dinâmico para garantir testes de rede reais.
 */
class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 1. Injeta os parâmetros obrigatórios da API do TMDB na Query da URL
        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .addQueryParameter(Constants.API_KEY_PARAM, BuildConfig.API_KEY)
            .build()

        // 2. ✅ CORRIGIDO: Adicionado cabeçalhos para detonar o cache e forçar o teste do Modo Avião
        val newRequest = request.newBuilder()
            .url(url)
            .header("Cache-Control", "no-cache, no-store, must-revalidate") // 🚨 Força o OkHttp a ignorar a memória local
            .header("Pragma", "no-cache") // Garante compatibilidade com HTTP/1.0 antigos da CloudFront
            .header("Expires", "0") // Diz ao sistema que o dado expira no mesmo segundo
            .build()

        return chain.proceed(newRequest)
    }
}