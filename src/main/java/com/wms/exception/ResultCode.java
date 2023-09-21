package com.wms.exception;


/*
*
*   2001表示添加操作
*   2002表示删除
*   2003表示修改
*   2004表示查询
*   末尾1表示成功，0表示失败
*
*
*
* */
public enum ResultCode {
    SUCCESS(200, "Success"),
    ERROR(-100, "Error"),

    SAVE_OK(20011, "Success"),
    DELETE_OK(20021, "Success"),
    UPDATE_OK(20031, "Success"),
    GET_OK(20011, "Success"),

    SAVE_ERR(-100, "Error"),
    DELETE_ERR(-100, "Error"),
    UPDATE_ERR(-100, "Error"),
    GET_ERR(-100, "Error"),

    SYSTEM_ERR(50001, "Error"),
    SYSTEM_TIME_ERR(50002, "Error"),
    SYSTEM_UNKNOW_ERR(59999, "Error"),
    BUSINESS_ERR(50001, "Error");
    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
