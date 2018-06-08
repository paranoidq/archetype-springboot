package me.webapp.web.support.messageConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.webapp.common.utils.JacksonUtil;
import me.webapp.domain.BaseDomain;
import me.webapp.web.common.ApiResponse;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * 定制透明的objectToJson转换策略
 *
 * Controller开发者无需返回固定的类，可以根据需要进行返回，该MessageConverter会自行转化为统一格式的JSON报文
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class TransparentJsonHttpResponseMessageConverter extends AbstractHttpMessageConverter {

    public TransparentJsonHttpResponseMessageConverter() {
        // 必须填写MediaType
        // SpringMVC中根据MediaType和对象类型进行判断是否由该converter进行转换
        setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_STREAM_JSON));
    }

    /**
     * 只要是非基本类型，都匹配
     *
     * update: 为了避免拦截swagger页面，这里需要针对返回类型进行检测，只有返回类型为{@link BaseDomain}的子类时，才使用该HttpMessageConverter进行转换
     *
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class aClass) {
        return !aClass.isPrimitive()
            && BaseDomain.class.isAssignableFrom(aClass);
    }



    /**
     * 如果请求报文也需要统一格式的话，可以实现该函数，并将http message转化为内部对象
     *
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 将返回对象解析为json格式后，发送到http流
     * @param object
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getHeaders().add("Content-Type", "application/json");
        httpOutputMessage.getBody().write(toFormalJson(object));
        httpOutputMessage.getBody().flush();
    }


    // TODO 编码问题？？？
    private byte[] toFormalJson(Object o) throws JsonProcessingException {
        ApiResponse response = ApiResponse.createOk(o);
        return JacksonUtil.toJSONStringPrettyFormat(response).getBytes();
    }
}
