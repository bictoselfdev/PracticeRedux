package com.example.practiceredux.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.Todo1Action

class Todo1Reducer : Reducer<AppState> {
    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is Todo1Action.Add -> {
                val tempState = state.copy(todo1 = ArrayList(state.todo1))
                tempState.todo1.add(action.todo)
                tempState
            }
            is Todo1Action.Clear -> {
                val tempState = state.copy(todo1 = ArrayList(state.todo1))
                tempState.todo1.clear()
                tempState
            }
            else -> {
                state
            }
        }

        return newState
    }

    companion object {
        const val TAG = "Todo1Reducer"
    }
}