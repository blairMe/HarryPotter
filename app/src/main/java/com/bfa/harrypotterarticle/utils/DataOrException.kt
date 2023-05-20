package com.bfa.harrypotterarticle.utils

class DataOrException<T, Boolean, E : Exception>(
    var data : T? = null,
    var loading : Boolean? = null,
    val e : E? = null
)