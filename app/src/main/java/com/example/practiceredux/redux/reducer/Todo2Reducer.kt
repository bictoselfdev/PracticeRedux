package com.example.practiceredux.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.Todo2Action

class Todo2Reducer : Reducer<AppState> {
    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is Todo2Action.Add -> {
                val tempState = state.copy(todo2 = ArrayList(state.todo2))
                tempState.todo2.add(action.todo)
                tempState
            }
            is Todo2Action.Clear -> {
                val tempState = state.copy(todo2 = ArrayList(state.todo2))
                tempState.todo2.clear()
                tempState
            }
            else -> {
                state
            }
        }

        return newState
    }

    companion object {
        const val TAG = "Todo2Reducer"
    }
}