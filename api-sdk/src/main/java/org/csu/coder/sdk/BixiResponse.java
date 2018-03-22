package org.csu.coder.sdk;

import java.io.Serializable;
import java.util.Map;

/**
 * @author by bixi.lx
 * @created on 2018 03 22 10:24
 */
public abstract class BixiResponse implements Serializable{
    private static final long serialVersionUID = -2405013877297826713L;

    private String errorCode;

    private String msg;

    private String subCode;

    private String subMsg;

    private String body;

    private Map<String, String> params;

    public BixiResponse() {}

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
