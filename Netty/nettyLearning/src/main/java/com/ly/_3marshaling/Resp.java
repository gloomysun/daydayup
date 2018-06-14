package com.ly._3marshaling;

import lombok.Data;

import java.io.Serializable;

@Data
public class Resp implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String responseMessage;
}
