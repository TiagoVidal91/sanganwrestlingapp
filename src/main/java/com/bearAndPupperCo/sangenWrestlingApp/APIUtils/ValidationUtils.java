package com.bearAndPupperCo.sangenWrestlingApp.APIUtils;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.OrderDirectionEnum;

import java.util.Arrays;

public class ValidationUtils {

    public static Boolean validateOrderDirection (String orderDirection){
        return Arrays.stream(OrderDirectionEnum.values())
                .map(Enum::name)
                .anyMatch(enumName -> enumName.equalsIgnoreCase(orderDirection));
    }
}
