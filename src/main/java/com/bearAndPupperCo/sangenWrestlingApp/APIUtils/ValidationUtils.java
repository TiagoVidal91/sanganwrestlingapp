package com.bearAndPupperCo.sangenWrestlingApp.APIUtils;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.OrderDirectionEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRONG_PARAM_MSG;

@Component
public class ValidationUtils {

    public void validateOrderDirection (String orderDirection){
        Arrays.stream(OrderDirectionEnum.values())
                .filter(direction -> direction.name().equals(orderDirection))
                .findFirst()
                .orElseThrow(() -> new WrongParamException(WRONG_PARAM_MSG));
    }

}
