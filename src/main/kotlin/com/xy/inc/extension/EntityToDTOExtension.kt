package com.xy.inc.extension

import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.entity.PointOfInterestEntity

fun PointOfInterestEntity.toDTO() = PointOfInterestDTO(
        name = this.name,
        coordinateX = this.coordinateX,
        coordinateY = this.coordinateY
)

fun List<PointOfInterestEntity>.toDTOList() = this.map { it.toDTO() }.toList()