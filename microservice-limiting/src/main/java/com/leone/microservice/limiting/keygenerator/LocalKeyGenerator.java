package com.leone.microservice.limiting.keygenerator;

import com.leone.microservice.limiting.anno.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
@Component
public class LocalKeyGenerator implements LockKeyGenerator {

    /**
     * 生成本地 key 策略 key-全类名.方法名(参数列表...)
     *
     * @param point
     * @return
     */
    @Override
    public String getLockKey(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        StringBuffer sb = new StringBuffer(localLock.key());
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (int i = 0; i < point.getArgs().length; i++) {
            joiner.add(point.getArgs()[i].toString());
        }
        sb.append("-").append(point.getTarget().getClass().getName()).append(".").append(method.getName()).append(joiner.toString());
        return sb.toString();
    }

}
