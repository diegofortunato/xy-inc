package com.xy.inc.service.impl

import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.entity.PointOfInterestEntity
import com.xy.inc.exception.PointOfInterestException
import com.xy.inc.extension.toDTO
import com.xy.inc.extension.toDTOList
import com.xy.inc.repository.PointOfInterestRepository
import com.xy.inc.service.PointOfInterestService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PointOfInterestServiceImpl(
        val pointOfInterestRepository: PointOfInterestRepository
) : PointOfInterestService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createPoint(pointEntity: PointOfInterestEntity): PointOfInterestDTO {
        log.info("Create Point service. point={}", pointEntity)

        val point = pointOfInterestRepository.findByName(pointEntity.name!!.toLowerCase())
        if (point.isPresent) throw PointOfInterestException("Point already exists")

        pointEntity.name = pointEntity.name!!.toLowerCase()
        val newPoint = pointOfInterestRepository.save(pointEntity)
        return newPoint.toDTO()
    }

    override fun getAllPointsPaging(page: Int, size: Int, sortBy: String): List<PointOfInterestDTO> {
        val paging = PageRequest.of(page, size, Sort.by(sortBy))
        val points = pointOfInterestRepository.findAll(paging)
        return points.content.toDTOList()
    }

    override fun getPointsFilteredByDistance(coordinateX: Int, coordinateY: Int, distance: Int): List<PointOfInterestDTO> {
        val points = pointOfInterestRepository.findAll()
        val pointsFilter = getPointsForDistance(coordinateX, coordinateY, distance, points)
        return pointsFilter.toDTOList()
    }

    fun getPointsForDistance(coordinateX: Int, coordinateY: Int, distance: Int, points: List<PointOfInterestEntity>):
            List<PointOfInterestEntity> {
        val pointsFilter = arrayListOf<PointOfInterestEntity>()
        points.forEach {
            val differenceX = calculateDifferenceX(it, coordinateX)
            val differenceY = calculateDifferenceY(it, coordinateY)
            val result = differenceX + differenceY
            if (result <= distance) pointsFilter.add(it)
        }
        return pointsFilter
    }

    fun calculateDifferenceX(point: PointOfInterestEntity, coordinateX: Int): Int {
        return if (point.coordinateX!! >= coordinateX) {
            point.coordinateX - coordinateX
        } else {
            coordinateX - point.coordinateX
        }
    }

    fun calculateDifferenceY(point: PointOfInterestEntity, coordinateY: Int): Int {
        return if (point.coordinateY!! >= coordinateY) {
            point.coordinateY - coordinateY
        } else {
            coordinateY - point.coordinateY
        }
    }
}