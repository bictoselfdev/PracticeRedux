package com.example.libredux

import java.util.concurrent.CopyOnWriteArrayList

class Store<State>(reducer: Reducer<State>, middlewareList: List<Middleware<State>>, initState: State) {
    private val dispatchers: MutableList<Dispatcher> = arrayListOf()
    private var subscriptions: MutableList<StoreSubscriber<State>> = CopyOnWriteArrayList()

    var state: State = initState
        private set(value) {
            field = value
            subscriptions.forEach { subscriber -> subscriber.newState(field) }
        }

    init {
        dispatchers.add(object : Dispatcher {
            @Synchronized
            override fun dispatch(action: Action) {
                val newState = reducer.invoke(action, state)
                if (state != newState) {
                    state = newState
                }
            }
        })

        middlewareList.map { middleware ->
            val next = dispatchers.first()
            dispatchers.add(0, object : Dispatcher {
                override fun dispatch(action: Action) {
                    middleware.invoke(this@Store, action, next)
                }
            })
        }
    }

    fun dispatch(action: Action) {
        dispatchers.first().dispatch(action)
    }

    fun subscribe(subscriber: StoreSubscriber<State>) {
        subscriptions.add(subscriber)
        subscriber.newState(state)
    }

    fun unsubscribe(subscriber: StoreSubscriber<State>) {
        subscriptions.remove(subscriber)
    }
}
