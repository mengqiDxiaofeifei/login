package com.mengqid.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    int code;

    com.mengqid.entity.common.Data data;

    String msg;
}
