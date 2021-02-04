package com.xy.inc.repository

import com.xy.inc.entity.PointOfInterestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PointOfInterestRepository : JpaRepository<PointOfInterestEntity, Int> {
    fun findByName(name: String): Optional<PointOfInterestEntity>
}