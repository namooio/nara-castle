package namoo.nara.castle.cp.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Aspect
@Component
public class RetryAspect {
    //
    private RetryTemplate retryTemplate;

    public RetryAspect() {
        //
        retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(5, Collections.<Class<? extends Throwable>, Boolean> singletonMap(OptimisticLockingFailureException.class, true)));
    }

    @Pointcut("execution(* namoo.nara.castle.domain.service.*.*(..))")
    public void serviceMethods() {
        //
    }

    @Around("serviceMethods()")
    public Object aroundServiceMethods(ProceedingJoinPoint joinPoint) {
        try {
            return retryTemplate.execute(retryContext -> joinPoint.proceed());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
