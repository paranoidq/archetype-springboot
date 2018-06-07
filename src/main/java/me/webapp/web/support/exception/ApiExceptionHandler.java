package me.webapp.web.support.exception;

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

import me.webapp.exception.AuthException;
import me.webapp.exception.DaoException;
import me.webapp.web.common.ApiErrorCode;
import me.webapp.exception.ServiceException;
import me.webapp.web.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * 通用异常处理接口
     *
     * 处理其他handler没有捕获到的异常，并渲染错误页面
     *
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ApiResponse throwable(Throwable t) {
        logger.error("Web应用异常", t);
        return ApiResponse.createError(ApiErrorCode.SERVER_UNKNOWN_ERROR);
    }


    /**
     * 权限验证异常处理接口
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public ApiResponse authException(AuthException e) {
        logger.error("权限验证异常", e);
        return ApiResponse.createError(ApiErrorCode.AUTH_ERROR);
    }


    /**
     * 服务调用异常处理接口
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ApiResponse serviceException(ServiceException e) {
        logger.error("service层异常", e);
        return ApiResponse.createError(ApiErrorCode.SERVER_UNKNOWN_ERROR);
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
