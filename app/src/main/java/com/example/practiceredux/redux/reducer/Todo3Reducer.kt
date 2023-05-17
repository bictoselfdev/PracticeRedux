package com.example.practiceredux.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.Todo3Action

class Todo3Reducer : Reducer<AppState> {
    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is Todo3Action.Add -> {
                val tempState = state.copy(todo3 = ArrayList(state.todo3))
                tempState.todo3.add(action.todo3)
                tempState
            }
            is Todo3Action.Clear -> {
                val tempState = state.copy(todo3 = ArrayList(state.todo3))
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
        const val TAG = "Todo3Reducer"
    }
}