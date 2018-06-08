package me.webapp.web.common;

/**
 * 接口调用应答码，用于构造{@link ApiResponse}对象，并返回客户端调用
 *
 * @author paranoidq
 * @since 1.0.0
 */
public enum ApiErrorCode {

    OK(0000, "接口调用成功"),


    AUTH_ERROR(1001, "权限验证失败"),


    UPLOAD_ERROR(8001, "文件上传失败"),


    SERVER_UNKNOWN_ERROR(9001, "服务器错误"),;

    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String hintMsg;

    ApiErrorCode(int code, String hintMsg) {
        this.code = code;
        this.hintMsg = hintMsg;
    }

    @Override
    public String toString() {
        return Integer.toString(this.code);
    }


    public int getCode() {
        return code;
    }

    public String getHintMsg() {
        return hintMsg;
    }
}
