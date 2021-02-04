package com.xy.inc.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "poitable")
data class PointOfInterestEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "point_id")
        var pointId: Int?,

        @Column(name = "point_name")
        var name: String?,

        @Column(name = "point_coordinate_x")
        val coordinateX: Int?,

        @Column(name = "point_coordinate_y")
        val coordinateY: Int?,
)