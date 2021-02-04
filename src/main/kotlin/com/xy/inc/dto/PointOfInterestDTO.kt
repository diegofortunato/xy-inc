package com.xy.inc.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PointOfInterestDTO(
    @field:NotEmpty(message = "Nome não pode ser nulo")
    @JsonProperty("name")
    val name: String?,

    @field:NotNull(message = "Coordenada X não pode ser nula")
    @field:Min(0, message = "Digite uma coordenada X válida")
    @field:Max(180, message = "Digite uma coordenada X válida")
    @JsonProperty("coordinate_x")
    val coordinateX: Int?,

    @field:NotNull(message = "Coordenada Y não pode ser nula")
    @field:Min(0, message = "Digite uma coordenada Y válida")
    @field:Max(180, message = "Digite uma coordenada Y válida")
    @JsonProperty("coordinate_y")
    val coordinateY: Int?,
)