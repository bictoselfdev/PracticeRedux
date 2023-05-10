package com.example.practiceredux

import android.app.Application
import android.content.Context
import com.example.libredux.Store
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.middleware.AppMiddleware
import com.example.practiceredux.redux.reducer.RootReducer

class MainApplication : Application() {

    init {
        instance = this
        rootReducer = RootReducer()
        appMiddleware = AppMiddleware()
        store = Store(rootReducer, listOf(appMiddleware), AppState())
    }

    companion object {
        lateinit var instance: MainApplication
        lateinit var store: Store<AppState>
        lateinit var rootReducer: RootReducer
        lateinit var appMiddleware: AppMiddleware

        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}