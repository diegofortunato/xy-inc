package com.xy.inc.service

import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.entity.PointOfInterestEntity
import com.xy.inc.repository.PointOfInterestRepository
import com.xy.inc.service.impl.PointOfInterestServiceImpl
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.Optional

@SpringBootTest
@AutoConfigureMockMvc
class PointOfInterestServiceImplTest {

    @MockBean
    private val pointOfInterestRepository: PointOfInterestRepository? = null

    @Autowired
    private val pointOfInterestService: PointOfInterestServiceImpl? = null

    @Test
    fun createPointSuccessTest() {
        val pointDTO = getPointDTO()

        given<Optional<PointOfInterestEntity>>(pointOfInterestRepository?.findByName(ArgumentMatchers.anyString()))
                .willReturn(Optional.empty())
        given<PointOfInterestEntity>(pointOfInterestRepository?.save(anyObject()))
                .willReturn(getPointEntity())

        val response = pointOfInterestService?.createPoint(getPointEntity())

        Assert.assertNotNull(response)
        Assert.assertTrue(response is PointOfInterestDTO)
        Assert.assertEquals(response?.name, pointDTO.name)
        Assert.assertEquals(response?.coordinateX, pointDTO.coordinateX)
        Assert.assertEquals(response?.coordinateY, pointDTO.coordinateY)
    }

    @Test
    fun getAllPointsPagingTest() {
        val paging = PageRequest.of(0, 10, Sort.by("name"))

        given<Page<PointOfInterestEntity>>(pointOfInterestRepository?.findAll(paging))
                .willReturn(getPointListPage())

        val response = pointOfInterestService?.getAllPointsPaging(0, 10, "name")

        Assert.assertNotNull(response)
        Assert.assertTrue(response is List<PointOfInterestDTO>)
        Assert.assertEquals(response?.size, 5)
    }

    @Test
    fun getPointsFilteredByDistanceTest() {
        given<List<PointOfInterestEntity>>(pointOfInterestRepository?.findAll())
                .willReturn(getPointList())

        val response = pointOfInterestService?.getPointsFilteredByDistance(20, 10, 10)
        Assert.assertNotNull(response)
        Assert.assertTrue(response is List<PointOfInterestDTO>)
        Assert.assertEquals(response?.size, 2)
    }

    @Test
    fun getPointsForDistanceTest() {
        val response = pointOfInterestService?.getPointsForDistance(20, 10, 10, getPointList()!!)

        Assert.assertNotNull(response)
        Assert.assertEquals(response?.size, 2)
    }

    @Test
    fun calculateDifferenceXWhenPointIsBigger() {
        val response = pointOfInterestService?.calculateDifferenceX(getPointEntity(), 7)

        Assert.assertNotNull(response)
        Assert.assertEquals(response?.toString(), "3")
    }

    @Test
    fun calculateDifferenceXWhenPointIsSmaller() {
        val response = pointOfInterestService?.calculateDifferenceX(getPointEntity(), 11)

        Assert.assertNotNull(response)
        Assert.assertEquals(response?.toString(), "1")
    }

    @Test
    fun calculateDifferenceYWhenPointIsBigger() {
        val response = pointOfInterestService?.calculateDifferenceY(getPointEntity(), 10)

        Assert.assertNotNull(response)
        Assert.assertEquals(response?.toString(), "5")
    }

    @Test
    fun calculateDifferenceYWhenPointIsSmaller() {
        val response = pointOfInterestService?.calculateDifferenceY(getPointEntity(), 20)

        Assert.assertNotNull(response)
        Assert.assertEquals(response?.toString(), "5")
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }

    fun getPointDTO() = PointOfInterestDTO(
            name = "Lanchonete",
            coordinateX = 10,
            coordinateY = 15
    )

    fun getPointEntity() = PointOfInterestEntity(
            pointId = 1,
            name = "Lanchonete",
            coordinateX = 10,
            coordinateY = 15
    )

    fun getPointListPage(): Page<PointOfInterestEntity>? {
        val pointOne = PointOfInterestEntity(1, "Floricultura", 19, 21)
        val pointTwo = PointOfInterestEntity(2, "Joalheria", 15, 12)
        val pointThree = PointOfInterestEntity(3, "Lanchonete", 10, 15)
        val pointFour = PointOfInterestEntity(4, "Posto", 31, 18)
        val pointFive = PointOfInterestEntity(5, "Pub", 12, 8)
        val list = arrayListOf(pointOne, pointTwo, pointThree, pointFour, pointFive)
        return PageImpl(list)
    }

    fun getPointList(): List<PointOfInterestEntity>? {
        val pointOne = PointOfInterestEntity(1, "Floricultura", 19, 21)
        val pointTwo = PointOfInterestEntity(2, "Joalheria", 15, 12)
        val pointThree = PointOfInterestEntity(3, "Lanchonete", 10, 15)
        val pointFour = PointOfInterestEntity(4, "Posto", 31, 18)
        val pointFive = PointOfInterestEntity(5, "Pub", 12, 8)
        return arrayListOf(pointOne, pointTwo, pointThree, pointFour, pointFive)
    }
}