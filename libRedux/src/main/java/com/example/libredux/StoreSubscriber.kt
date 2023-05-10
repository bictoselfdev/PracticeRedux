package com.example.libredux

interface StoreSubscriber<State> {
    fun newState(state: State)
}