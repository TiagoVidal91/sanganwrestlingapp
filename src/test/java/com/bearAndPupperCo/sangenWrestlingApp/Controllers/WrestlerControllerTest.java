package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlerSrv;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WrestlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WrestlerSrv wrestlerSrv;

    private int page;
    private int size = 10;
    private int brandId = 1;
    private int lockerId = 1;
    private String order = "NAME";
    private String orderDirection = "ASC";


    @BeforeEach
    void setUp(){
        page = 1;
        size = 10;
        brandId = 1;
        lockerId = 1;
        order = "NAME";
        orderDirection = "ASC";
    }

    @Test
    void findAllWrestlersByParamsTest() throws Exception {
        //Arrange
        WrestlerMainTableDTO wrestler1 = new WrestlerMainTableDTO();
        WrestlerMainTableDTO wrestler2 = new WrestlerMainTableDTO();
        List<WrestlerMainTableDTO> wrestlerList = new ArrayList<>(Arrays.asList(wrestler1,wrestler2));
        PaginatedResponse<WrestlerMainTableDTO> wrestlerListPage =
                new PaginatedResponse<>(wrestlerList,page,size,20,2);

        String expectedResult = new Gson().toJson(wrestlerListPage);

        when(wrestlerSrv.findAllWrestlersByParams(page,size, brandId, lockerId, order, orderDirection))
                .thenReturn(wrestlerListPage);

        //Act & Assert
        mockMvc.perform(get("/teiai-api/wrestler/findAllWrestlersByParam")
                .param("pageNumber", "1")
                .param("pageSize","10")
                .param("brandId","1")
                .param("lockerId","1")
                .param("order",order)
                .param("orderDirection", orderDirection))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

    }

    @Test
    void missingPageNumberTest() throws Exception {
        mockMvc.perform(get("/teiai-api/wrestler/findAllWrestlersByParam")
                        .param("pageSize","10")
                        .param("brandId","1")
                        .param("lockerId","1")
                        .param("order",order)
                        .param("orderDirection", orderDirection))
                .andExpect(status().isInternalServerError())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andExpect(content().string(containsString("Required request parameter 'pageNumber'")));
    }

    @Test
    void missingPageSizeTest() throws Exception {
        mockMvc.perform(get("/teiai-api/wrestler/findAllWrestlersByParam")
                        .param("pageNumber", "1")
                        .param("brandId","1")
                        .param("lockerId","1")
                        .param("order",order)
                        .param("orderDirection", orderDirection))
                .andExpect(status().isInternalServerError())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andExpect(content().string(containsString("Required request parameter 'pageSize'")));
    }

    @Test
    void missingOrderTest() throws Exception {
        mockMvc.perform(get("/teiai-api/wrestler/findAllWrestlersByParam")
                        .param("pageNumber", "1")
                        .param("pageSize","10")
                        .param("brandId","1")
                        .param("lockerId","1")
                        .param("orderDirection", orderDirection))
                .andExpect(status().isInternalServerError())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andExpect(content().string(containsString("Required request parameter 'order'")));
    }

    @Test
    void missingOrderDirectionTest() throws Exception {
        mockMvc.perform(get("/teiai-api/wrestler/findAllWrestlersByParam")
                        .param("pageNumber", "1")
                        .param("pageSize","10")
                        .param("brandId","1")
                        .param("lockerId","1")
                        .param("order",order))
                .andExpect(status().isInternalServerError())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException))
                .andExpect(content().string(containsString("Required request parameter 'orderDirection'")));
    }

}