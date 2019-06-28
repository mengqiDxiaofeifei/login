package com.mengqid.entity;


import com.mengqid.constant.ResponseConstant;
import com.mengqid.utils.CheckUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/30.
 * 接口响应实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    /**
     * 响应码
     **/
    private Integer code;
    /**
     * 响应消息（错误消息）
     **/
    private String msg;

    /**
     * 响应数据
     **/
    private Object data;

    public static Response buildErrorResponse() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_ERROR.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_ERROR.MSG)
                .build();
    }

    public static Response buildErrorResponse(String msg) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_ERROR.CODE)
                .msg(msg)
                .build();
    }

    public static Response buildSuccessResponse() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_SUCCESS.MSG)
                .build();
    }

    public static Response buildSuccessResponse(Object data) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_SUCCESS.MSG)
                .data(data)
                .build();
    }

    public static Response buildSuccessResponse(String key, Object data) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_SUCCESS.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_SUCCESS.MSG)
                .data(key, data)
                .build();
    }

    public static Response buildParamEmptyError(String param) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_PARAM_EMPTY.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_PARAM_EMPTY.MSG + param)
                .build();
    }

    public static Response buildParamFormatError(String param) {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_PARAM_FORMAT_ERROR.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_PARAM_FORMAT_ERROR.MSG + param)
                .build();
    }

    /**
     * token为空或者从token中拿数据失败，一定要返回这个Response
     *
     * @return
     */
    public static Response buildAuthError() {
        return new Builder()
                .code(ResponseConstant.RESPONSE_CODE_AUTH_ERROR.CODE)
                .msg(ResponseConstant.RESPONSE_CODE_AUTH_ERROR.MSG)
                .build();
    }

    public static Response buildNoResourceError(String resource) {
        return new Builder()
                .code(ResponseConstant.WEB_CODE_NOT_FOUND.CODE)
                .msg(ResponseConstant.WEB_CODE_NOT_FOUND.MSG + resource)
                .build();
    }

    public static class Builder {

        private Integer code;
        private String msg;
        private Map data;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(String key, Object data) {
            if (this.data == null) {
                this.data = new HashMap<>();
            }
            this.data.put(key, data);
            return this;
        }

        public Builder data(Object data) {
            if (CheckUtil.isEmpty(data)) {
                return this;
            }

            if (this.data == null) {
                this.data = new HashMap<>();
            }

            if (data instanceof Map) {
                this.data.putAll((Map) data);
                return this;
            }
            String key = "";
            if (data instanceof List) {
                key = getSimpleName(((List) data).get(0).getClass());
            } else {
                key = getSimpleName(data.getClass());
            }
            if (!CheckUtil.isEmpty(key)) {
                this.data.put(key, data);
            }
            return this;
        }

        public Response build() {
            Response response = new Response();
            response.code = this.code;
            response.msg = this.msg;
            response.data = this.data;
            return response;
        }
    }

    public static Response buildResponse(Integer code, String msg, Object data) {
        return new Builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    private static String getSimpleName(Class clazz) {
        return clazz.getSimpleName().toLowerCase();
    }
}
