package me.webapp.web.common;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.io.Serializable;

/**
 * 当采用rest方式返回时，默认应答HTTP 200
 * <p>
 * ApiResponse作为被{@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}处理的对象，必须要有getter和setter方法
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.OK)
@JsonSerialize(using = ApiResponse.ApiResponseSerializer.class)
public class ApiResponse implements Serializable {

    /**
     * 状态码
     */
    private ApiErrorCode code;
    /**
     * 报错原因，当状态码为{@link ApiErrorCode#OK}时，该字段为空
     */
    private String error;
    /**
     * 返回报文信息
     */
    private Object data;
    /**
     * 返回应答的时间戳
     */
    private long timestamp;

    // TODO. other useful fields ???


    public ApiResponse(ApiErrorCode code, String error, Object msg) {
        this.code = code;
        this.error = error;
        this.data = msg;
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
     * @param error
     * @return
     */
    public static ApiResponse createError(ApiErrorCode errorCode, String error) {
        return createError(errorCode, error, "");
    }

    /**
     * 报错时的应答对象实例
     *
     * @param errorCode
     * @param error
     * @param message
     * @return
     */
    public static ApiResponse createError(ApiErrorCode errorCode, String error, Object message) {
        return new ApiResponse(errorCode, error, message);
    }


    public ApiErrorCode getCode() {
        return code;
    }

    public void setCode(ApiErrorCode code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

            gen.writeFieldName("error");
            gen.writeString(apiResponse.error);

            gen.writeFieldName("data");
            gen.writeObject(apiResponse.data);

            gen.writeFieldName("timestamp");
            gen.writeString(Long.toString(apiResponse.timestamp));

            gen.writeEndObject();
        }
    }
}
