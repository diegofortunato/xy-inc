package com.xy.inc.service

import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.entity.PointOfInterestEntity

interface PointOfInterestService {
    fun createPoint(pointEntity: PointOfInterestEntity): PointOfInterestDTO
    fun getAllPointsPaging(page: Int, size: Int, sortBy: String): List<PointOfInterestDTO>
    fun getPointsFilteredByDistance(coordinateX: Int, coordinateY: Int, distance: Int): List<PointOfInterestDTO>
}