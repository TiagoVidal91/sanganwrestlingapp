package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.ValidationUtils;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRONG_PARAM_MSG;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WrestlerServiceImplTest {

    @InjectMocks
    WrestlerServiceImpl wrestlerService;
    @Mock
    WrestlerRepoImpl wrestlerRepo;

    @Mock
    ValidationUtils validationUtils;

    private int page = 1;
    private int size = 10;
    private int brandId = 1;
    private int lockerId = 1;
    private String order = "NAME";
    private String orderDirection = "ASC";

    @Test
    void findAllWrestlersByParamsTest() {
        //Assert
        WrestlerMainTableDTO wrestler1 = new WrestlerMainTableDTO();
        WrestlerMainTableDTO wrestler2 = new WrestlerMainTableDTO();
        wrestler1.setInRingName("Adorable Ivy");
        wrestler1.setInRingName("Tiger Tia");
        List<WrestlerMainTableDTO> wrestlerList = new ArrayList<>(Arrays.asList(wrestler1, wrestler2));
        String orderBy = WrestlerMainTableEnum.getColumnName(order);

        when(validationUtils.validateOrderDirection(orderDirection)).thenReturn(true);
        when(wrestlerRepo.findWrestlerListByParams(page,size,brandId,lockerId,orderBy,orderDirection))
                .thenReturn(wrestlerList);
        when(wrestlerRepo.getTotalWrestlerCount(brandId,lockerId)).thenReturn(wrestlerList.size());

        //Act
        PaginatedResponse<WrestlerMainTableDTO> response = wrestlerService
                .findAllWrestlersByParams(page,size,brandId,lockerId,order,orderDirection);

        //Assert
        assertEquals(response.getContent(),wrestlerList);
        assertEquals(response.getNumber(), page);
        assertEquals(response.getSize(), size);
        assertEquals(response.getTotalElements(),2);
        assertEquals(response.getTotalPages(),1);
    }

    @Test
    void wrongOrderTypeTest() {

        //Arrange
        order= "WRONG_ORDER_TYPE";

        //Act & Assert
        String message = assertThrows(WrongParamException.class, () -> wrestlerService
                .findAllWrestlersByParams(page,size,brandId,lockerId,order,orderDirection)).getMessage();
        assertEquals(message, WRONG_PARAM_MSG);

        verify(validationUtils,times(1)).validateOrderDirection(orderDirection);
    }

    @Test
    void wrongOrderDirectionTest() {

        //Arrange
        orderDirection = "WRONG_ORDER_DIRECTION";

        //Act & Assert
        String message = assertThrows(WrongParamException.class, () -> wrestlerService
                .findAllWrestlersByParams(page,size,brandId,lockerId,order,orderDirection)).getMessage();
        assertEquals(message, WRONG_PARAM_MSG);

        verify(validationUtils, times(1)).validateOrderDirection(orderDirection);
    }
}