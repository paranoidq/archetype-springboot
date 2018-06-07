package me.webapp.web.common;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

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
