package com.bearAndPupperCo.sangenWrestlingApp.Enum;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WrestlerMainTableEnumTest {

    @ParameterizedTest
    @CsvSource({
            "NAME, in_ring_name",
            "VICTORIES, total_victories",
            "LOSSES, total_losses",
            "STREAK, wrestling_streak",
            "WIN_PERCENTAGE, percentage_of_victories"
    })
    void getColumnNameTest(String enumValue, String expectedColumnName) {
        assertEquals(expectedColumnName, WrestlerMainTableEnum.getColumnName(enumValue));
    }
}