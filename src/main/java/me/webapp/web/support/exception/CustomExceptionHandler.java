package me.webapp.web.support.exception;

import me.webapp.exception.AuthException;
import me.webapp.exception.DaoException;
import me.webapp.exception.ServiceException;
import me.webapp.web.common.ApiErrorCode;
import me.webapp.web.common.ApiResponse;
import me.webapp.web.common.MediaTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 *
 * 三种方式：
 *      1. 直接使用{@link org.springframework.cache.jcache.interceptor.SimpleExceptionCacheResolver}
 *      2. 实现{@link HandlerExceptionResolver}
 *      3. 使用注解：{@link org.springframework.web.bind.annotation.ControllerAdvice} + {@link org.springframework.web.bind.annotation.ExceptionHandler}
 *         注意单独使用ExceptionHandle必须与异常方法在同一个类中
 *
 * TODO: i18n支持的errorMessage
 *
 * @author paranoidq
 * @since 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    /**
     * 通用异常处理接口
     *
     * 处理其他handler没有捕获到的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse> handlerGeneralException(Exception e) {
        logger.error("Web应用异常", e);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ApiResponse response = ApiResponse.createError(ApiErrorCode.SERVER_UNKNOWN_ERROR, e.getMessage());
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 权限验证异常处理接口
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public final ResponseEntity<ApiResponse> authException(AuthException e) {
        logger.error("权限验证异常", e);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ApiResponse response = ApiResponse.createError(ApiErrorCode.AUTH_ERROR, e.getMessage());
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.FORBIDDEN);
    }


    /**
     * 服务调用异常处理接口
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ApiResponse> serviceException(ServiceException e) {
        logger.error("service层异常", e);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ApiResponse response = ApiResponse.createError(ApiErrorCode.SERVER_UNKNOWN_ERROR, e.getMessage());
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.FORBIDDEN);
    }


    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DaoException.class)
    public ApiResponse daoException(DaoException e) {
        logger.error("dao层异常", e);
        return ApiResponse.createError(ApiErrorCode.SERVER_UNKNOWN_ERROR);
    }

}
