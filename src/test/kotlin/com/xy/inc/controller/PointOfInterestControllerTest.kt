package com.xy.inc.controller

import com.xy.inc.constant.APIConstant
import com.xy.inc.dto.PointOfInterestDTO
import com.xy.inc.service.PointOfInterestService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.File
import java.nio.file.Files

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class PointOfInterestControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val pointService: PointOfInterestService? = null

    @Test
    fun createPoint200Test() {
        val pointDTO = getPointDTO()

        `when`(pointService?.createPoint(anyObject())).thenReturn(pointDTO)

        val request = getJson("request-valid.json")

        mvc!!.perform(
                MockMvcRequestBuilders
                        .post(APIConstant.BASE_API + APIConstant.POINT_POST_RESOURCE)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(pointDTO.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.coordinate_x").value(pointDTO.coordinateX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.coordinate_y").value(pointDTO.coordinateY))
    }

    @Test
    fun getPointPagination200Test() {
        val pointList = getPointList()

        `when`(pointService?.getAllPointsPaging(0, 10, "name")).thenReturn(pointList)

        mvc!!.perform(
                MockMvcRequestBuilders
                        .get(APIConstant.BASE_API + APIConstant.POINT_GET_ALL_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(pointList.size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(pointList[0].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].coordinate_x").value(pointList[0].coordinateX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].coordinate_y").value(pointList[0].coordinateY))
    }

    @Test
    fun getPointDetail200Test() {
        val pointList = getPointListFilter()

        `when`(pointService?.getPointsFilteredByDistance(20, 10, 10)).thenReturn(pointList)

        mvc!!.perform(
                MockMvcRequestBuilders
                        .get(APIConstant.BASE_API + APIConstant.POINT_GET_FILTER_RESOURCE)
                        .param("coordinateX", "20")
                        .param("coordinateY", "10")
                        .param("distance", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(pointList.size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value(pointList[0].name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].coordinate_x").value(pointList[0].coordinateX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].coordinate_y").value(pointList[0].coordinateY))
    }

    fun getPointDTO() = PointOfInterestDTO(
            name = "Lanchonete",
            coordinateX = 10,
            coordinateY = 15
    )

    fun getPointList(): List<PointOfInterestDTO> {
        val pointOne = PointOfInterestDTO("Floricultura", 19, 21)
        val pointTwo = PointOfInterestDTO("Joalheria", 15, 12)
        val pointThree = PointOfInterestDTO("Lanchonete", 10, 15)
        val pointFour = PointOfInterestDTO("Posto", 31, 18)
        val pointFive = PointOfInterestDTO("Pub", 12, 8)
        return arrayListOf(pointOne, pointTwo, pointThree, pointFour, pointFive)
    }

    fun getPointListFilter(): List<PointOfInterestDTO> {
        val pointOne = PointOfInterestDTO("Joalheria", 15, 12)
        val pointTwo = PointOfInterestDTO("Lanchonete", 10, 15)
        val pointThree = PointOfInterestDTO("Pub", 12, 8)
        return arrayListOf(pointOne, pointTwo, pointThree)
    }

    fun getJson(fileName: String): String {
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(fileName).file)
        return String(Files.readAllBytes(file.toPath()))
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}
