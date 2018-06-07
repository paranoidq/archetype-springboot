package me.webapp.support.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * RestAPI接口访问日志
 *
 * 实现方式：AOP
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
@Order(1)
public class RestApiAccessLogging {
    private static Logger logger = LoggerFactory.getLogger(RestApiAccessLogging.class);

    /**
     * rest接口中标记了{@link org.springframework.web.bind.annotation.RequestMapping}的方法为切点
     */
    @Pointcut("execution(public * me.webapp.web.open.rest..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void apiPointCut() {

    }

    /**
     * 打印API访问请求日志
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("apiPointCut()")
    public void loggingAccess(JoinPoint joinPoint) throws Throwable {
        // 通过RequestContextHolder获取请求
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 记录请求信息
        logger.info("[RestApi] URL : " + request.getRequestURL().toString());
        logger.info("[RestApi] HTTP_METHOD : " + request.getMethod());
        logger.info("[RestApi] IP : " + request.getRemoteAddr());
        logger.info("[RestApi] CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("[RestApi] ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }


    /**
     * 打印API访问应答日志
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "apiPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("[RestApi] RESPONSE : " + ret);
    }

}
