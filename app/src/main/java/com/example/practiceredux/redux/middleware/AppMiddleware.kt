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

    private var mainActivity: MainActivity? = null

    override fun invoke(store: Store<AppState>, action: Action, next: Dispatcher) {
        Log.d(TAG, action.toString())

        when (action) {
            is CommonAction.FragmentChange -> {
                replaceFragment(action.fragment)
            }
        }
        next.dispatch(action)
    }

    fun setActivity(activity: MainActivity) {
        mainActivity = activity
    }

    private fun replaceFragment(fragment: Fragment) {
        if (mainActivity != null) {
            mainActivity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        const val TAG = "AppMiddleware"
    }
}