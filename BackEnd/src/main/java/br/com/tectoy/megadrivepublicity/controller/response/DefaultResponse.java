package br.com.tectoy.megadrivepublicity.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultResponse {
    private int code;
    private String message;
}
