package com.gdt.controller.advices;


import com.gdt.dto.ErrorDTO;
import com.gdt.enums.ErrorsCode;
import com.gdt.exceptions.BadrequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gdt.enums.ErrorsCode.USER_EXIST;

@ControllerAdvice
public class ControllersAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadrequestException.class)
    public @ResponseBody ErrorDTO  handleIllegalArgumentException(Exception exception){
        exception.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(USER_EXIST);
        errorDTO.setMessage(exception.getMessage());
        return errorDTO;
    }
}
