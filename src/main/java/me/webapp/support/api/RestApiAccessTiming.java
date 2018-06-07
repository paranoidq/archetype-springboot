package me.webapp.support.api;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * RestAPI接口访问时间统计
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Aspect
@Component
@Order(2)
public class RestApiAccessTiming {
    private static Logger logger = LoggerFactory.getLogger(RestApiAccessTiming.class);

    private ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();


    /**
     * rest接口中标记了{@link org.springframework.web.bind.annotation.RequestMapping}的方法为切点
     */
    @Pointcut("execution(public * me.webapp.web.open.rest..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void apiPointCut() {

    }


    /**
     * 统计访问开始时间
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("apiPointCut()")
    public void loggingAccess(JoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatchThreadLocal.set(stopWatch);
        stopWatch.start();
    }


    /**
     * 打印API访问应答日志
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "apiPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();
        logger.info("[RestApi] INVOKING TIME: " + stopWatch.getTime() + "(ms)");
        // For gc
        stopWatchThreadLocal.remove();
    }

}
