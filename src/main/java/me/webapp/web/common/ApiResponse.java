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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * 当采用rest方式返回时，默认应答HTTP 200
 *
 * ApiResponse作为被{@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}处理的对象，必须要有getter和setter方法
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.OK)
@JsonSerialize(using = ApiResponse.ApiResponseSerializer.class)
public class ApiResponse implements Serializable{

    /**
     * 状态码
     */
    private ApiErrorCode code;
    /**
     * 报错原因，当状态码为{@link ApiErrorCode#OK}时，该字段为空
     */
    private String reason;
    /**
     * 返回报文信息
     */
    private Object msg;
    /**
     * 返回应答的时间戳
     */
    private long timestamp;

    // TODO. other useful fields ???


    public ApiResponse(ApiErrorCode code, String reason, Object msg) {
        this.code = code;
        this.reason = reason;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功时的应答对象实例
     *
     * @param msg
     * @return
     */
    public static ApiResponse createOk(Object msg) {
        return new ApiResponse(ApiErrorCode.OK, "", msg);
    }


    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @return
     */
    public static ApiResponse createError(ApiErrorCode errorCode) {
        return createError(errorCode, errorCode.getHintMsg());
    }


    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @param reason
     * @return
     */
    public static ApiResponse createError(ApiErrorCode errorCode, String reason) {
        return createError(errorCode, reason, "");
    }

    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @param reason
     * @param message
     * @return
     */
    public static ApiResponse createError(ApiErrorCode errorCode, String reason, Object message) {
        return new ApiResponse(errorCode, reason, message);
    }


    public ApiErrorCode getCode() {
        return code;
    }

    public void setCode(ApiErrorCode code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public static class ApiResponseSerializer extends StdSerializer<ApiResponse> {

        public ApiResponseSerializer() {
            super(ApiResponse.class);
        }

        protected ApiResponseSerializer(Class t) {
            super(t);
        }

        @Override
        public void serialize(ApiResponse apiResponse, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();

            gen.writeFieldName("code");
            gen.writeString(apiResponse.code.toString());

            gen.writeFieldName("reason");
            gen.writeString(apiResponse.reason);

            gen.writeFieldName("msg");
            gen.writeString(String.valueOf(apiResponse.msg));

            gen.writeFieldName("timestamp");
            gen.writeString(Long.toString(apiResponse.timestamp));

            gen.writeEndObject();
        }
    }
}
