package com.ly._3marshaling;

import lombok.Data;

@Data
public class Resp {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String responseMessage;
}
