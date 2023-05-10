package com.example.practiceredux.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.practiceredux.redux.*

class RootReducer : Reducer<AppState> {

    private val commonReducer = CommonReducer()
    private val todo1Reducer = Todo1Reducer()
    private val todo2Reducer = Todo2Reducer()
    private val todo3Reducer = Todo3Reducer()

    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is CommonAction -> commonReducer.invoke(action, state)
            is Todo1Action -> todo1Reducer.invoke(action, state)
            is Todo2Action -> todo2Reducer.invoke(action, state)
            is Todo3Action -> todo3Reducer.invoke(action, state)
            else -> state
        }

        return newState
    }

    companion object {
        const val TAG = "RootReducer"
    }
}