package com.xy.inc.response

data class ErrorResponse(
    val data: String,
    val codigo: Int,
    val erro: String,
    val descricao: String
)
