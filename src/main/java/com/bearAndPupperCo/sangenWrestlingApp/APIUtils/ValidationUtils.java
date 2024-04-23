package com.bearAndPupperCo.sangenWrestlingApp.APIUtils;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.OrderDirectionEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ValidationUtils {

    public Boolean validateOrderDirection (String orderDirection){
        return Arrays.stream(OrderDirectionEnum.values())
                .map(Enum::name)
                .anyMatch(enumName -> enumName.equalsIgnoreCase(orderDirection));
    }
}
