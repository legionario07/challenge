package br.com.challenge.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ListRepoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}