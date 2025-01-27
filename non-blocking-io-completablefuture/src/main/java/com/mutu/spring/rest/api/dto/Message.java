package com.mutu.spring.rest.api.dto;

import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Message {
    @Id
    private String id;
    private String messageId;
    private String message;    
}
