package com.example.practiceredux.redux

import com.example.libredux.Action

sealed class CommonAction : Action {
    object TodoAllClear : CommonAction()
}

sealed class Todo1Action : Action {
    data class Add(val todo1: String) : Todo1Action()
    object Clear : Todo1Action()
}

sealed class Todo2Action : Action {
    data class Add(val todo2: String) : Todo2Action()
    object Clear : Todo2Action()
}

sealed class Todo3Action : Action {
    data class Add(val todo3: String) : Todo3Action()
    object Clear : Todo3Action()
}