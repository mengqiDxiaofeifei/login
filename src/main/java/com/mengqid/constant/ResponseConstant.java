package com.mengqid.constant;

import lombok.AllArgsConstructor;

/**
 * 返回提示
 *
 * @author
 * @date 2018/12/26
 */
@AllArgsConstructor
public enum ResponseConstant {

    /**
     * S 响应码
     */
    // 请求失败
    RESPONSE_CODE_NO_RESOURCE(500, "服务器错误"),
    // 请求成功
    RESPONSE_CODE_SUCCESS(200, "响应成功"),

    RESPONSE_CODE_BAD_REQUEST(400, "请求失败"),

    // web资源显响应码 404
    WEB_CODE_NOT_FOUND(404, "资源不存在"),
    // 权限不足
    RESPONSE_CODE_AUTH_ERROR(403, "签名认证失败,请检查后重试"),
    // 认证不通过
    WEB_CODE_UNAUTHORIZED(401, "认证不通过"),

    RESPONSE_CODE_ERROR(-1, "失败，请核对参数"),
    RESPONSE_CODE_PARAM_EMPTY(-2, "失败，请求参数不能为空: "),
    RESPONSE_CODE_PARAM_FORMAT_ERROR(-3, "失败，请求参数格式错误: "),
    /***其他平台登录相关*/
    LOGIN_SUCCESS(200, "登陆成功"),
    LOGIN_NO_PARAM(10001, "用户名或密码不能为空"),
    LOGIN_NO_UUID(10002, "UUID不能为空"),
    LOGIN_UUID_UNAVAILABLE(10003, "UUID不可用请重新登录"),
    LOGIN_TOKEN_UNAVAILABLE(10004, "token is unavailable"),
    LOGIN_PLATFORM_UNAVAILABLE(10005, "平台CODE无效"),
    LOGIN_PERMISSION_UNAVAILABLE(10006, "无权登录"),
    LOGIN_RESOURCE_UNAVAILABLE(10007, "无法访问该平台资源"),
    LOGIN_SYS_UNAVAILABLE(10008, "系统无法访问"),

    /***API-统一响应码*/
    API_PARAM_NONE(10001, "请求参数不能为空"),
    API_PARAM_ERROR(10002, "请核对参数"),
    API_PARAM_HAS_SPACE(10003, "参数不能含有空格"),
    API_ID_NO_EXIST(10004, "api id 不可用"),
    /***API-用户信息响应码*/
    API_ACCOUNT_ERROR_MOBILE(20001, "手机号格式错误"),
    API_ACCOUNT_TYPE_NONE(20002, "账号类型不能为空"),
    API_ACCOUNT_EXIST(20003, "用户名已存在"),
    API_ACCOUNT_MOBILE_MOBILE(20004, "手机号已被注册"),
    API_ACCOUNT_UNIT_NONE(20005, "组织为必填项"),
    API_ACCOUNT_HEADIMG_NONE(20006, "请上传头像"),
    API_ACCOUNT_PASSWORD_ERROR(20007, "密码格式错误"),
    API_ACCOUNT_UNAVAILABLE(20008, "账号不可用"),
    /***API-角色相关响应吗*/
    API_ROLE_NAME_UNAVAILABLE(30001, "角色名称已存在"),
    API_ROLE_CODE_UNAVAILABLE(30002, "角色CODE已存在"),
    API_ROLE_PLATFORM_UNAVAILABLE(30003, "角色无法指定所属平台"),
    API_ROLE_NO_EXIST(30004, "角色不存在"),
    /***API-资源响应吗*/
    API_RESOURCE_PID_UNAVAILABLE(40001, "父ID不可用"),
    API_RESOURCE_URL_UNAVAILABLE(40002, "url不可用"),
    API_RESOURCE_DEL_UNAVAILABLE(40003, "请先删除最底层节点");

    /**
     * E 响应码
     */
    public int CODE;
    public String MSG;
}
