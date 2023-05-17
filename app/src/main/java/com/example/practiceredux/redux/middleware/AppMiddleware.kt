package com.example.practiceredux.redux.middleware

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.libredux.Action
import com.example.libredux.Dispatcher
import com.example.libredux.Middleware
import com.example.libredux.Store
import com.example.practiceredux.MainActivity
import com.example.practiceredux.R
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.CommonAction

class AppMiddleware : Middleware<AppState> {

    override fun invoke(store: Store<AppState>, action: Action, next: Dispatcher) {
        Log.d(TAG, action.toString())

        // 해당 예제에서는 미들웨어를 통해 비동기처리할 사항이 없기 때문에 껍데기로 두었다.. 바로 Reducer 에게 전달

        next.dispatch(action)
    }

    companion object {
        const val TAG = "AppMiddleware"
    }
}