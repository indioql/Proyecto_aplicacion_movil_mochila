plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.postureapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.postureapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation("androidx.core:core-ktx:1.13.1") // Se mantiene la versión más reciente y se elimina la duplicada.
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // --- Dependencias de Firebase añadidas ---
    // Importa la BoM de Firebase (Bill of Materials) para gestionar automáticamente las versiones
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // Dependencia para Firebase Authentication para gestionar inicio de sesión y registro
    implementation("com.google.firebase:firebase-auth-ktx")

    // Dependencia para Firebase Firestore para guardar datos de usuario en la nube
    implementation("com.google.firebase:firebase-firestore-ktx")
    // --- Fin de las dependencias de Firebase ---

    // Dependencias existentes
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Dependencias de testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
