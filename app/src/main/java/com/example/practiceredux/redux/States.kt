package com.example.practiceredux.redux

import androidx.fragment.app.Fragment

data class AppState(
    var currentFragment: Fragment? = null,
    var todo1: ArrayList<String> = ArrayList(),
    var todo2: ArrayList<String> = ArrayList(),
    var todo3: ArrayList<String> = ArrayList()
) {

//    override fun toString(): String {
//        return toPrettyString()
//    }
//
//    fun toPrettyString(indentSize: Int = 2) = " ".repeat(indentSize).let { indent ->
//        System.err.println("[TEST] " + toString())
//        toString()
//            .replace(", ", ",\n$indent")
//            .replace("(", "(\n$indent")
//            .dropLast(1) + "\n)"
//    }
}