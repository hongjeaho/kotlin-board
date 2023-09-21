package com.example.border.common.extension

fun Boolean.ifTrue(func: () -> Unit) = if (this) func.invoke() else Unit
fun Boolean.ifFalse(func: () -> Unit) = if (!this) func.invoke() else Unit
