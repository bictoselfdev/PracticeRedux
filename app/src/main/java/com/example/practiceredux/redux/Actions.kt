package com.example.practiceredux.redux

import androidx.fragment.app.Fragment
import com.example.libredux.Action

sealed class CommonAction : Action {
    data class FragmentChange(val fragment: Fragment) : CommonAction()
    object TodoAllClear : CommonAction()
}

sealed class Todo1Action : Action {
    data class Add(val todo: String) : Todo1Action()
    object Clear : Todo1Action()
}

sealed class Todo2Action : Action {
    data class Add(val todo: String) : Todo2Action()
    object Clear : Todo2Action()
}

sealed class Todo3Action : Action {
    data class Add(val todo: String) : Todo3Action()
    object Clear : Todo3Action()
}