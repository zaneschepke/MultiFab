import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    signing
}

android {
    namespace = "${Constants.PACKAGE}.mutlifab"
    compileSdk = Constants.COMPILE_SDK

    defaultConfig {
        minSdk = Constants.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Constants.COMPILER_VERSION
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {

    // compose ui
    implementation(libs.androidx.ui)

    //test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // compose preview
    implementation(libs.androidx.ui.tooling.preview)

    // compose activity
    implementation(libs.androidx.activity.compose)

    // compose material3
    implementation(libs.androidx.material3)

    // compose constraint layout
    implementation(libs.androidx.constraintlayout.compose)
    // lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.zaneschepke"
            artifactId = "multifab"
            version = providers.gradleProperty("multiFabVersionName").get()
            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set("MultiFab Compose")
                description.set("A simple MultiFab implementation for Jetpack Compose.")
                url.set("https://github.com/zaneschepke/MultiFab")

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/zaneschepke/MultiFab")
                    developerConnection.set("scm:git:https://github.com/zaneschepke/MultiFab")
                    url.set("https://github.com/zaneschepke/MultiFab")
                }
                developers {
                    organization {
                        name.set("Zane Schepke")
                        url.set("https://zaneschepke.com")
                    }
                    developer {
                        name.set("Zane Schepke")
                        email.set("support@zaneschepke.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/zaneschepke/multifab")
            credentials {
                username = getLocalProperty("GITHUB_USER")
                password = getLocalProperty("GITHUB_TOKEN")
            }
        }
        maven {
            name = "sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getLocalProperty("MAVEN_CENTRAL_USER")
                password = getLocalProperty("MAVEN_CENTRAL_PASS")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}