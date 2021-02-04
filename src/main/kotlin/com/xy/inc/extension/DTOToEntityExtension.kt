package com.xy.inc.extension

import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.entity.PointOfInterestEntity

fun PointOfInterestDTO.toEntity() = PointOfInterestEntity(
        pointId = null,
        name = this.name,
        coordinateX = this.coordinateX,
        coordinateY = this.coordinateY
)