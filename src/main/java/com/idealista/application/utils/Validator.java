package com.idealista.application.utils;

import com.idealista.application.bean.Level;
import com.idealista.application.exception.ValidationException;
import com.idealista.application.exception.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Validator {
    /*public static void validateQualityListing(){

    }
    public static void validatePublicListing(){

    }*/
    public static void validateCalculateScore(String id) throws ValidationException {
        List<ErrorDTO> errors = new ArrayList<>();
        if(id == null)
            errors.add(ErrorDTO.builder().field("id").description("Unique identifier is null").level(Level.ERROR).build());
        if(Boolean.FALSE.equals(id == null) && Boolean.FALSE.equals(StringUtils.isNumeric(id)))
            errors.add(ErrorDTO.builder().field("id").description("Unique identifier is not numeric").level(Level.ERROR).build());

        if(Boolean.FALSE.equals(errors.isEmpty())) throw new ValidationException(HttpStatus.BAD_REQUEST, "Errors in 'id' field", errors);
    }
}
