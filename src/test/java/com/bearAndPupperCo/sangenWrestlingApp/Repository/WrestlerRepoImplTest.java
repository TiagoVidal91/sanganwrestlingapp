package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class WrestlerRepoImplTest {

    @Autowired
    private WrestlerRepoImpl wrestlerRepo;

    @ParameterizedTest
    @MethodSource("wrestlerTableParams")
    public void findWrestlerListByRingNameTest(Integer brandId, Integer lockerId, String orderDirection, String orderBy, String expectedName) {
        //Arrange
        int size = 10;
        int page = 1;

        //Act
        List<WrestlerMainTableDTO> wrestlers = wrestlerRepo.findWrestlerListByParams(page, size, brandId, lockerId,
                orderBy, orderDirection);

        //Assert
        assertNotNull(wrestlers, "Wrestler list should not be null");
        assertEquals(expectedName, wrestlers.stream().findFirst().get().getInRingName());
    }

    @ParameterizedTest
    @MethodSource("totalCountParams")
    public void findTotalWrestlerCountTest(Integer brandId, Integer lockerId, int totalExpected){
        int totalWrestler = wrestlerRepo.getTotalWrestlerCount(brandId, lockerId);
        assertEquals(totalWrestler,totalExpected);
    }

    private static List<Object[]> wrestlerTableParams() {
        return Arrays.asList(new Object[][] {
                { null, null, "ASC", "in_ring_name", "Aaron Schultz" },
                { null, null, "DESC", "in_ring_name", "Tiger Tia" },
                { 1, null, "ASC", "in_ring_name", "Lance Drake" },
                { 1, null, "DESC", "in_ring_name", "Tiger Tia" },
                { 2, null, "ASC", "in_ring_name", "Aaron Schultz" },
                { 2, null, "DESC", "in_ring_name", "Kurt Mackey" },
                { 1, 1, "ASC", "in_ring_name", "Rebecca Lawrence" },
                { 1, 1, "DESC", "in_ring_name", "Tiger Tia" },
                { 1, 2, "ASC", "in_ring_name", "Lance Drake" },
                { 1, 2, "DESC", "in_ring_name", "Raymond Hunter" },
                { 2, 1, "ASC", "in_ring_name", "Adorable Ivy" },
                { 2, 1, "DESC", "in_ring_name", "Akane Sakamoto" },
                { 2, 2, "ASC", "in_ring_name", "Aaron Schultz" },
                { 2, 2, "DESC", "in_ring_name", "Kurt Mackey" },

                { null, null, "DESC", "total_victories", "Akane Sakamoto" },
                { null, null, "ASC", "total_victories", "Kurt Mackey" },
                { 1, 1, "DESC", "total_victories", "Tiger Tia" },
                { 1, 2, "DESC", "total_victories", "Lance Drake" },
                { 2, 1, "DESC", "total_victories", "Akane Sakamoto" },
                { 2, 2, "DESC", "total_victories", "Aaron Schultz" },
                { 1, 1, "ASC", "total_victories", "Rebecca Lawrence" },
                { 1, 2, "ASC", "total_victories", "Raymond Hunter" },
                { 2, 1, "ASC", "total_victories", "Adorable Ivy" },
                { 2, 2, "ASC", "total_victories", "Kurt Mackey" },

                { null, null, "DESC", "total_losses", "Adorable Ivy" },
                { null, null, "ASC", "total_losses", "Aaron Schultz" },
                { 1, 1, "DESC", "total_losses", "Rebecca Lawrence" },
                { 1, 2, "DESC", "total_losses", "Lance Drake" },
                { 2, 1, "DESC", "total_losses", "Adorable Ivy" },
                { 2, 2, "DESC", "total_losses", "Kurt Mackey" },
                { 1, 1, "ASC", "total_losses", "Tiger Tia" },
                { 1, 2, "ASC", "total_losses", "Raymond Hunter" },
                { 2, 1, "ASC", "total_losses", "Akane Sakamoto" },
                { 2, 2, "ASC", "total_losses", "Aaron Schultz" },

                { null, null, "DESC", "wrestling_streak", "Raymond Hunter" },
                { 1, 1, "DESC", "wrestling_streak", "Tiger Tia" },
                { 1, 2, "DESC", "wrestling_streak", "Raymond Hunter" },
                { 2, 1, "DESC", "wrestling_streak", "Akane Sakamoto" },
                { 2, 2, "DESC", "wrestling_streak", "Aaron Schultz" },
                { null, null, "ASC", "wrestling_streak", "Adorable Ivy" },
                { 1, 1, "ASC", "wrestling_streak", "Rebecca Lawrence" },
                { 1, 2, "ASC", "wrestling_streak", "Lance Drake" },
                { 2, 1, "ASC", "wrestling_streak", "Adorable Ivy" },
                { 2, 2, "ASC", "wrestling_streak", "Kurt Mackey" },

                { null, null, "DESC", "percentage_of_victories", "Aaron Schultz" },
                { null, null, "ASC", "percentage_of_victories", "Rebecca Lawrence" },
                { 1, 1, "DESC", "percentage_of_victories", "Tiger Tia" },
                { 1, 2, "DESC", "percentage_of_victories", "Raymond Hunter" },
                { 2, 1, "DESC", "percentage_of_victories", "Akane Sakamoto" },
                { 2, 2, "DESC", "percentage_of_victories", "Aaron Schultz" },
                { 1, 1, "ASC", "percentage_of_victories", "Rebecca Lawrence" },
                { 1, 2, "ASC", "percentage_of_victories", "Lance Drake" },
                { 2, 1, "ASC", "percentage_of_victories", "Adorable Ivy" },
                { 2, 2, "ASC", "percentage_of_victories", "Kurt Mackey" },
        });
    }
    private static List<Object[]> totalCountParams(){
        return Arrays.asList(new Object[][]{
                { null, null, 8 },
                { 1, null, 4 },
                { 2, null, 4 },
                { null, 1, 4 },
                { null, 2, 4 },
                { 1, 1, 2 },
                { 1, 2, 2 },
                { 2, 1, 2 },
                { 2, 2, 2 }
        });
    }
}