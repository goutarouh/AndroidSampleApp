import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("com.google.dagger.hilt.android").get())
                add("kapt", libs.findLibrary("com.google.dagger.hilt.compiler").get())
            }
        }
    }
}