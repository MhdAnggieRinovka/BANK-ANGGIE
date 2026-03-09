package com.bank.assigmenBNI.webResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
public class WebResponse<T> {
    private Integer status_code;
    private String message;
    private T data;
}
