package com.davanok.dvnklamp.androidApp

import android.app.Application
import com.davanok.dvnklamp.di.AndroidAppGraph
import dev.zacsweers.metro.createGraphFactory

class App: Application() {
    val appGraph by lazy { createGraphFactory<AndroidAppGraph.Factory>().create(this) }
}