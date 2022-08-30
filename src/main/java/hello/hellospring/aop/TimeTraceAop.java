package hello.hellospring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Slf4j
@Aspect
//@Component // AOP의 경우에는 컴포넌트 스캔보다는 주의를 요하므로 Bean 등록을 선호 but 순환참조 문제를 해결해야 한다.
public class TimeTraceAop {

    @Pointcut("execution(* hello.hellospring..*(..))")
    private void allClasses() {}

    /**
     * SpringConfig.class에서 자기 자신인 TimeTraceAop를 @Bean으로 등록하여 호출하고 있으므로 순환참조가 발생한다.
     * 따라서 AOP 적용대상에서 제외 해주어야함.
     */
    @Pointcut("!target(hello.hellospring.config.SpringConfig)")
    private void excludeSpringConfig() {}

    @Around("allClasses() && excludeSpringConfig()")
    public Object execution(ProceedingJoinPoint pjp) throws Throwable {
        long startTimeMs = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
        } finally {
            long endTimeMs = System.currentTimeMillis();
            long resultTimeMs = endTimeMs - startTimeMs;
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            String className = pjp.getSignature().getDeclaringTypeName();
            String methodName = methodSignature.getMethod().getName();
            log.info("{}.{}() result time: {}ms", className, methodName, resultTimeMs);
        }

        return result;
    }

}
