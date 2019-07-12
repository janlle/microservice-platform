package com.leone.microservice.limiting.intercepter;

import com.leone.microservice.limiting.anno.CacheLock;
import com.leone.microservice.limiting.config.RedisLockHelper;
import com.leone.microservice.limiting.keygenerator.CatchKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-30
 **/
@Aspect
@Component
public class CatchMethodInterceptor {

    @Autowired
    private CatchKeyGenerator catchKeyGenerator;

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Around("@annotation(com.leone.microservice.limiting.anno.CacheLock)")
    public Object interceptor(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new IllegalArgumentException("lock key don't null...");
        }

        String lockKey = catchKeyGenerator.getLockKey(point);
        String value = UUID.randomUUID().toString();
        System.err.println(lockKey + " -- " + value);
        try {
            if (!redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit())) {
                throw new IllegalArgumentException("重复提交");
            }
            try {
                return point.proceed();
            } catch (Throwable throwable) {
                throw new IllegalArgumentException("系统异常");
            }
        } finally {
            // 如果演示的话需要注释该代码，实际应该放开
            // redisLockHelper.unlock(lockKey, value);
        }
    }


}
