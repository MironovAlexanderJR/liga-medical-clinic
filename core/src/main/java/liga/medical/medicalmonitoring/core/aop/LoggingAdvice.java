package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.commondto.DebugLogDto;
import liga.medical.commondto.ExceptionLogDto;
import liga.medical.medicalmonitoring.core.clients.CommonClient;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAdvice {

    private final CommonClient commonClient;

    @Value("${spring.application.name}")
    private String appName;

    @Pointcut("@annotation(liga.medical.medicalmonitoring.core.annoatations.dbLog)")
    public void loggableMethods() {}

    @Around("loggableMethods()")
    public Object rabbitMethodsLogger(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String className = joinPoint.getTarget().getClass().toString();
        String methodName = joinPoint.getSignature().getName();
        Object[] array = joinPoint.getArgs();

        String methodParams = className + " " + methodName + " " + mapper.writeValueAsString(array);

        DebugLogDto debugLog = new DebugLogDto();
        debugLog.setSystemTypeId(appName);
        debugLog.setMethodParams(methodParams);
        commonClient.saveDebugLog(debugLog);

        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            ExceptionLogDto exceptionLog = new ExceptionLogDto();
            exceptionLog.setSystemTypeId(appName);
            exceptionLog.setMethodParams(methodParams);
            commonClient.saveExceptionLog(exceptionLog);
        }
        return object;
    }
}
