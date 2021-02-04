package com.xy.inc.constant

class APIConstant {

    companion object {
        const val BASE_API = "/points"
        const val POINT_POST_RESOURCE = ""
        const val POINT_GET_ALL_RESOURCE = ""
        const val POINT_GET_FILTER_RESOURCE = "/distance"

        const val ERROR_400 = "Ponto de interesse já existe."
        const val DETAILS_ERROR_400 = "Existe um erro na requisição, ponto de interesse já existe."
        const val ERROR_500 = "Erro interno do servidor."
        const val DETAILS_ERROR_500 = "Ocoreu um erro interno no servidor, " +
                "entre em contato com o administrador do sistema."
    }
}