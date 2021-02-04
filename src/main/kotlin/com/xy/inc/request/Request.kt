package com.xy.inc.request

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class Request<T> (
        @field:NotNull(message = "Request não pode ser nulo")
        @field:Valid
        var request: T
)