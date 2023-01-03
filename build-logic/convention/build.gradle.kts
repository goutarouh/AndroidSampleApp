plugins {
    `kotlin-dsl`
}

group = "com.github.goutarouh.androidsampleapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "androidsampleapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "androidsampleapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }

}