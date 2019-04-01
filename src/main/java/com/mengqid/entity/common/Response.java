package com.mengqid.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    int code;

    Object data;

    String msg;
}
