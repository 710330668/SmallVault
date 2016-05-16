-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses

-keep class com.example.administrator.smallvault.ui.** {*; }
-keep class android.** {*; }
-keep public class * extends android.content.ContentProvider

-dontwarn butterknife.**
-keep class butterknife.**{*;}

-dontwarn org.hamcrest.**
-keep class org.hamcrest.**{*;}

-dontwarn com.github.mikephil.charting.**
-keep class com.github.mikephil.charting.**{*;}

-dontwarn android.support.annotation.**
-keep class android.support.annotation.**{*;}

-dontwarn android.support.v4.**
-keep class android.support.v4.**{*;}


