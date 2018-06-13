package com.ly._3marshaling;

import lombok.Data;

import java.io.Serializable;

@Data
public class Req implements Serializable {
    private static final long SerialVersionUID = 1L;
    private String id;
    private String name;
    private String requestMessage;
    private byte[] attachment;
}
