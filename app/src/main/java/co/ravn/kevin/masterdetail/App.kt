package co.ravn.kevin.masterdetail

import android.app.Application
import co.ravn.kevin.masterdetail.networking.Api
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject lateinit var api: Api
}