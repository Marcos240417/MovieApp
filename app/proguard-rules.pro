# =========================================================================
# KOTLIN & SERIALIZATION (Blindagem de Dados e Metadados)
# =========================================================================

# Mantém os metadados do Kotlin essenciais para Reflection (usado pelo Koin)
-keep class kotlin.Metadata { *; }

# Mantém atributos de anotação necessários para o Kotlinx Serialization funcionar
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

# Garante que os campos mapeados da API (TMDB) não mudem de nome
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
}

# =========================================================================
# KOIN (Injeção de Dependências & ViewModels)
# =========================================================================

# Impede que as classes internas do Koin percam os tipos genéricos em runtime
-keepattributes *Annotation*,Signature

# REGRA DE OURO SÊNIOR: Impede o R8 de ofuscar os construtores dos seus ViewModels.
# Sem isso, o Koin vai crashar em produção ao tentar criar as telas do Compose.
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    public <init>(...);
}

# =========================================================================
# LOGGING (Timber)
# =========================================================================

# Otimização de Produção: Remove todas as chamadas de Log do Timber automaticamente.
# Isso impede que mensagens com dados sensíveis ou URLs apareçam no Logcat do usuário final.
-assumenosideeffects class timber.log.Timber {
    public static void d(...);
    public static void v(...);
    public static void i(...);
}