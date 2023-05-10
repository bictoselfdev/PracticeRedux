package com.example.practiceredux.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.CommonAction

class CommonReducer : Reducer<AppState> {
    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is CommonAction.FragmentChange -> {
                state.copy(currentFragment = action.fragment)
            }
            is CommonAction.TodoAllClear -> {
                val tempState = state.copy(todo1 = ArrayList(state.todo1), todo2 = ArrayList(state.todo2), todo3 = ArrayList(state.todo3))
                tempState.todo1.clear()
                tempState.todo2.clear()
                tempState.todo3.clear()
                tempState
            }
            else -> {
                state
            }
        }

        return newState
    }

    companion object {
        const val TAG = "CommonReducer"
    }
}