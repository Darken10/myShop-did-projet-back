package com.did.MyShop.Exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExeptionResponse {
    private int code;
    private String description;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;

}
