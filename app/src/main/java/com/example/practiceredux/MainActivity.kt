package com.example.practiceredux

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.libredux.StateSubscriber
import com.example.libredux.Store
import com.example.practiceredux.databinding.ActivityMainBinding
import com.example.practiceredux.fragment.Todo1Fragment
import com.example.practiceredux.fragment.Todo2Fragment
import com.example.practiceredux.fragment.Todo3Fragment
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.CommonAction

class MainActivity : AppCompatActivity(), StateSubscriber<AppState> {

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

        setListener()

        store.subscribe(this)
    }

    private fun setListener() {
        binding.btnTodoAllClear.setOnClickListener {
            store.dispatch(CommonAction.TodoAllClear)
        }

        binding.btnTodo1.setOnClickListener {
            replaceFragment(todo1Fragment)
            binding.tvCurrentTodo.text = "현재 화면 : Todo1"
        }

        binding.btnTodo2.setOnClickListener {
            replaceFragment(todo2Fragment)
            binding.tvCurrentTodo.text = "현재 화면 : Todo2"
        }

        binding.btnTodo3.setOnClickListener {
            replaceFragment(todo3Fragment)
            binding.tvCurrentTodo.text = "현재 화면 : Todo3"
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}