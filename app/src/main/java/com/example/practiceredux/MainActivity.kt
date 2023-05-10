package com.example.practiceredux

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.libredux.Store
import com.example.libredux.StoreSubscriber
import com.example.practiceredux.databinding.ActivityMainBinding
import com.example.practiceredux.fragment.Todo1Fragment
import com.example.practiceredux.fragment.Todo2Fragment
import com.example.practiceredux.fragment.Todo3Fragment
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.CommonAction

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    lateinit var binding: ActivityMainBinding

    private val store: Store<AppState> by lazy {
        MainApplication.store
    }

    private val todo1Fragment = Todo1Fragment.newInstance()
    private val todo2Fragment = Todo2Fragment.newInstance()
    private val todo3Fragment = Todo3Fragment.newInstance()

    override fun newState(state: AppState) {
        Log.d(TAG, "newState $state")

        binding.btnTodo1.text = String.format("Todo1 (%d)", state.todo1.size)
        binding.btnTodo2.text = String.format("Todo2 (%d)", state.todo2.size)
        binding.btnTodo3.text = String.format("Todo3 (%d)", state.todo3.size)

        when (state.currentFragment) {
            is Todo1Fragment -> binding.tvCurrentTodo.text = "현재 화면 : Todo1"
            is Todo2Fragment -> binding.tvCurrentTodo.text = "현재 화면 : Todo2"
            is Todo3Fragment -> binding.tvCurrentTodo.text = "현재 화면 : Todo3"
            else -> binding.tvCurrentTodo.text = "현재 화면 : 없음"
        }
    }

    override fun onResume() {
        Log.d(TAG, "onResume")

        super.onResume()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
        store.unsubscribe(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()
        setListener()

        MainApplication.appMiddleware.setActivity(this)
        store.subscribe(this)
    }

    private fun initUI() {

    }

    private fun setListener() {
        binding.btnTodoAllClear.setOnClickListener {
            store.dispatch(CommonAction.TodoAllClear)
        }

        binding.btnTodo1.setOnClickListener {
            store.dispatch(CommonAction.FragmentChange(todo1Fragment))
        }

        binding.btnTodo2.setOnClickListener {
            store.dispatch(CommonAction.FragmentChange(todo2Fragment))
        }

        binding.btnTodo3.setOnClickListener {
            store.dispatch(CommonAction.FragmentChange(todo3Fragment))
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}