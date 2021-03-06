package dependencies

const val COMPILE_SDK = 29
const val MIN_SDK = 21
const val TARGET_SDK = 29

const val VKOTLIN = "1.4.10"

const val ANDROID_PLUGIN = "com.android.tools.build:gradle:4.2.0-alpha07"
const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VKOTLIN"

private const val VCOUROUTINE = "1.3.4"
const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:$VKOTLIN"
const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VCOUROUTINE"
const val UI_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VCOUROUTINE"

const val KTX = "androidx.core:core-ktx:1.3.1"
const val ACTIVITYX = "androidx.activity:activity-ktx:1.2.0-alpha08"
const val FRAGMENTX = "androidx.fragment:fragment-ktx:1.3.0-alpha08"

const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha04"

const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.1.0"
const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.0.1"
const val CARDVIEW = "androidx.cardview:cardview:1.0.0"

const val WEBKIT = "androidx.webkit:webkit:1.0.0"

const val MATERIAL = "com.google.android.material:material:1.2.1"
const val PAGING = "androidx.paging:paging-runtime:2.1.0"
const val BROWSER = "androidx.browser:browser:1.0.0"

const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.1.0"
const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:2.0.0"
const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:2.1.0"

const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

const val SAVEDSTATE = "androidx.savedstate:savedstate:1.0.0-alpha02"
const val SAVEDSTATE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-alpha01"

const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:1.0.0"

const val ANDROID_ANNOTATION = "androidx.annotation:annotation:1.0.0"

private const val VDAGGER = "2.29"
const val DAGGER_RUNTIME = "com.google.dagger:dagger:$VDAGGER"
const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$VDAGGER"

const val GLIDE = "com.github.bumptech.glide:glide:4.9.0"
const val COIL = "io.coil-kt:coil:0.9.5"

const val VCOMPOSE = "1.0.0-alpha03"
const val COMPOSE = "androidx.compose.runtime:runtime:$VCOMPOSE"
const val COMPOSE_UI = "androidx.compose.ui:ui:$VCOMPOSE"
const val COMPOSE_UI_TOOLING = "androidx.ui:ui-tooling:$VCOMPOSE"
const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:$VCOMPOSE"
const val COMPOSE_FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:$VCOMPOSE"
const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:$VCOMPOSE"
const val COMPOSE_MATERIAL = "androidx.compose.material:material:$VCOMPOSE"
const val COMPOSE_MATERIAL_ICON = "androidx.compose.material:material-icons-extended:$VCOMPOSE"

const val JUNIT = "junit:junit:4.13-beta-1"
const val TRUTH = "com.google.truth:truth:0.42"
const val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
const val TEST_RUNNER = "androidx.test:runner:1.1.1"
const val TEST_RULE = "androidx.test:rules:1.1.0"
const val ESPRESSO = "androidx.test.espresso:espresso-core:3.1.1"
