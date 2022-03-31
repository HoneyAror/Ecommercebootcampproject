package com.example.bootcamp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ResponseDTO {
    Integer status;
    Object data;
    String message;

    public static
    ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object resobj1){
        Map<String, Object> map=new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",resobj1.toString());
        return new ResponseEntity<Object>(map,status);

    }
}
