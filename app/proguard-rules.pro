# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ------------------------------
# General Android Rules
# ------------------------------

# Keep Parcelable classes (used in Room, Nav arguments)
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep annotations
-keepattributes *Annotation*

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep all classes with @Keep annotation
-keep @androidx.annotation.Keep class *

# Keep Application classes
-keep class * extends android.app.Application

# Keep ViewModels (used by Jetpack Compose)
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# ------------------------------
# Kotlin Specific
# ------------------------------
-keepclassmembers class kotlin.Metadata { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

# ------------------------------
# Jetpack Compose
# ------------------------------
# Keep Compose generated code
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }
-dontwarn androidx.compose.**
-keep class * extends androidx.compose.runtime.Composable { *; }

# Keep @Composable functions
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# ------------------------------
# Room Database
# ------------------------------
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }

# Keep entities, DAOs, converters
-keepclassmembers class * {
    @androidx.room.* <fields>;
    @androidx.room.* <methods>;
}

-keepclassmembers class * {
    @androidx.room.TypeConverters <methods>;
}

# Keep database classes
-keep class * extends androidx.room.RoomDatabase { *; }

# Keep generated code by Room (like DAO_Impl)
-keep class *_Impl { *; }
-dontwarn androidx.room.**

# ------------------------------
# Coroutines
# ------------------------------
-dontwarn kotlinx.coroutines.**

# ------------------------------
# Gson / JSON parsing
# ------------------------------
-keep class com.google.gson.** { *; }
-keep class com.janitri.pregnancyvitals.data.model.** { *; }
-keepclassmembers class com.janitri.pregnancyvitals.data.model.** {
    <fields>;
}

# ------------------------------
# Firebase / Analytics / Other Libraries (optional)
# ------------------------------
# If you use third-party libraries, add rules as per their documentation

# ------------------------------
# Misc
# ------------------------------
-dontwarn android.support.**
-dontwarn android.arch.lifecycle.**
-dontwarn javax.annotation.**


#================================================================================
# General Android & Kotlin Rules
#================================================================================

# Keep custom Parcelable classes.
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Keep all classes annotated with @Keep.
-keep @androidx.annotation.Keep class *

# Keep enum methods that are accessed via reflection.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve Kotlin metadata, which is essential for reflection.
-keep,allowobfuscation,allowshrinking class kotlin.Metadata

#================================================================================
# Hilt - Dependency Injection
#================================================================================
# These rules are essential for Hilt's code generation to work after obfuscation.

-keep class dagger.hilt.internal.aggregatedroot.AggregatedRoot
-keep @dagger.hilt.android.HiltAndroidApp class *
-keep class * extends androidx.lifecycle.ViewModel
-keep class * implements dagger.hilt.EntryPoint
-keep @dagger.hilt.EntryPoint class *
-keep @dagger.Module class * {
    @dagger.Provides *;
}
-keep class * implements dagger.hilt.InstallIn
-keep @dagger.hilt.InstallIn class *

#================================================================================
# Room - Database
#================================================================================
# Often, only keeping the database class is needed.

-keep class * extends androidx.room.RoomDatabase

#================================================================================
# Coroutines
#================================================================================
# Prevents obfuscation from breaking coroutine dispatchers.

-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory
-keepnames class kotlinx.coroutines.android.HandlerContext

#================================================================================
# Jetpack Compose

# Keep Composable functions
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# Keep classes used by Compose tooling
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Keep preview functions for Android Studio
-keepclassmembers class * {
    @androidx.compose.ui.tooling.preview.Preview <methods>;
}
