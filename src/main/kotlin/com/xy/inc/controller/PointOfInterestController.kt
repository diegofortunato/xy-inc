package com.xy.inc.controller

import com.xy.inc.constant.APIConstant
import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.extension.toEntity
import com.xy.inc.request.Request
import com.xy.inc.response.Response
import com.xy.inc.service.PointOfInterestService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class PointOfInterestController(private val pointOfInterestService: PointOfInterestService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.POINT_POST_RESOURCE)
    fun createPointOfInterest(@Valid @RequestBody pointDTO: Request<PointOfInterestDTO>):
            ResponseEntity<Response<PointOfInterestDTO>> {

        log.info("POST ${APIConstant.POINT_POST_RESOURCE} for body {}", pointDTO)

        val pointEntity = pointDTO.request.toEntity()
        val point = pointOfInterestService.createPoint(pointEntity)

        return ResponseEntity
                .created(URI.create(URLEncoder.encode(APIConstant.BASE_API + APIConstant.POINT_GET_ALL_RESOURCE, "UTF-8")))
                .body(Response(data = point))
    }

    @GetMapping(APIConstant.POINT_GET_ALL_RESOURCE)
    fun getAllPointsOfInterests(
            @RequestParam(defaultValue = "0", required = false) page: Int,
            @RequestParam(defaultValue = "10", required = false) size: Int,
            @RequestParam(defaultValue = "name", required = false) sortBy: String
    ):
            ResponseEntity<Response<List<PointOfInterestDTO>>> {

        log.info("GET All ${APIConstant.POINT_GET_ALL_RESOURCE}")

        val points = pointOfInterestService.getAllPointsPaging(page, size, sortBy)
        return ResponseEntity.ok(Response(data = points))
    }

    @GetMapping(APIConstant.POINT_GET_FILTER_RESOURCE)
    fun getOnePointOfInterestByProximity(
            @RequestParam(required = true) coordinateX: Int,
            @RequestParam(required = true) coordinateY: Int,
            @RequestParam(required = true) distance: Int,
    ):
            ResponseEntity<Response<List<PointOfInterestDTO>>> {

        log.info("GET ${APIConstant.POINT_GET_FILTER_RESOURCE} for coordinate X and Y {}, {}", coordinateX, coordinateY)

        val point = pointOfInterestService.getPointsFilteredByDistance(coordinateX, coordinateY, distance)
        return ResponseEntity.ok(Response(data = point))
    }
}
