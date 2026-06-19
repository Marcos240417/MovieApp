import java.io.FileInputStream
import java.util.Properties
// ✅ SÊNIOR: Imports obrigatórios adicionados para o Kotlin DSL reconhecer as classes Java


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ✅ SÊNIOR: Isolado a leitura do arquivo de propriedades dentro do escopo que o consome
        val apiKeyProperties = Properties()
        val apiKeyPropertiesFile = rootProject.file("apikey.properties")
        if (apiKeyPropertiesFile.exists()) {
            apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))
        }

        // Configurações de injeção segura no BuildConfig
        buildConfigField(
            "String",
            "API_KEY",
            "\"${apiKeyProperties.getProperty("API_KEY", "")}\""
        )

        buildConfigField(
            "String",
            "BASE_URL",
            "\"${apiKeyProperties.getProperty("BASE_URL", "")}\""
        )

        buildConfigField(
            "String",
            "BASE_URL_IMAGE",
            "\"${apiKeyProperties.getProperty("BASE_URL_IMAGE", "")}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true // ✅ Essencial para gerar a classe BuildConfig com suas chaves
    }

    sourceSets.named("main") {
        kotlin.directories += "src/main/kotlin"
    }
    // ✅ ADICIONE ESTE BLOCO para dar o opt-in na nova regra do Kotlin
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
}
dependencies {

    // --- ANDROID CORE & BASE ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.timber)

    // --- JETPACK COMPOSE CORE (Gerenciados pelo BOM) ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3) // ✅ Unificado aqui

    // --- JETPACK COMPOSE COMPLEMENTOS ---
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.saveable)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)

    // --- IMAGENS (Carregamento Assíncrono) ---
    implementation(libs.coil.compose)

    // --- LIFECYCLE & VIEWMODEL ---
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // --- COROUTINES & FLOW (Processamento Assíncrono) ---
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // --- KOIN (Injeção de Dependência) ---
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // --- ROOM DATABASE (Persistência Local Offline-First) ---
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // --- DATASTORE (Preferências Chave-Valor) ---
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)

    // --- PAGING 3 (Paginação Reativa) ---
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // --- REDE & COMUNICAÇÃO (Retrofit & OkHttp) ---
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // --- SERIALIZAÇÃO & TRATAMENTO DE JSON ---
    // ✅ Otimizado: Removido o Gson para manter a consistência com o convertedor do Kotlinx
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // --- TESTES UNITÁRIOS ---
    testImplementation(libs.junit)

    // --- TESTES DE INSTRUMENTAÇÃO (Android/UI) ---
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // --- DEBBUGING / TOOLING ---
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}