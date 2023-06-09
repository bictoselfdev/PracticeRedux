package com.example.practiceredux.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libredux.Store
import com.example.libredux.StateSubscriber
import com.example.practiceredux.MainApplication
import com.example.practiceredux.R
import com.example.practiceredux.adapter.TodoAdapter
import com.example.practiceredux.databinding.FragmentTodoBinding
import com.example.practiceredux.redux.AppState
import com.example.practiceredux.redux.Todo3Action

class Todo3Fragment : Fragment(), StateSubscriber<AppState> {

    lateinit var binding: FragmentTodoBinding

    private val store: Store<AppState> by lazy {
        MainApplication.store
    }

    override fun newState(state: AppState) {
        Log.d(TAG, "newState $state")

        binding.rvTodo.adapter = TodoAdapter(state.todo3)
    }

    override fun onResume() {
        Log.d(TAG, "onResume")

        super.onResume()
        store.subscribe(this)
    }

    override fun onStop() {
        Log.d(Todo1Fragment.TAG, "onStop")

        super.onStop()
        store.unsubscribe(this)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
        store.unsubscribe(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)

        initUI()
        setListener()

        return binding.root
    }

    private fun initUI() {
        val mLayoutManager = LinearLayoutManager(MainApplication.getContext())
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTodo.layoutManager = mLayoutManager
        binding.rvTodo.adapter = TodoAdapter(store.state.todo3)
    }

    private fun setListener() {
        binding.btnAdd.setOnClickListener {
            val todo = binding.etTodo.text.toString()
            store.dispatch(Todo3Action.Add(todo))
        }

        binding.btnClear.setOnClickListener {
            store.dispatch(Todo3Action.Clear)
        }
    }

    companion object {
        fun newInstance(): Todo3Fragment{
            val args = Bundle().apply {
                putString("name", "Todo3Fragment")
            }

            val fragment = Todo3Fragment()
            fragment.arguments = args
            return fragment
        }

        const val TAG = "Todo3Fragment"
    }
}