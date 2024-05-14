package com.bearAndPupperCo.sangenWrestlingApp.APIUtils;

import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationUtilsTest {

    @InjectMocks
    ValidationUtils validationUtils;

    @ParameterizedTest
    @ValueSource(strings = {"ASC", "DESC"})
    void validateOrderDirectionASCTest(String orderDirection) {
        //Act & Assert
        assertDoesNotThrow(() -> validationUtils.validateOrderDirection(orderDirection));
    }
    @Test
    void validateOrderDirectionErrorTest() {
        //Arrange
        String orderDirection = "WRONG_ORDER_DIRECTION";

        //Act & Assert
        assertThrows(WrongParamException.class, () -> validationUtils.validateOrderDirection(orderDirection));
    }
}