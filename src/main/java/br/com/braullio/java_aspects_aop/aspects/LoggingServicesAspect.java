package br.com.braullio.java_aspects_aop.aspects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingServicesAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingServicesAspect.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Pointcut("execution(* br.com.braullio.java_aspects_aop.services.SenderService.email(..))")
    public void serviceLayer() {}

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
       // Cria um StringBuilder para o log JSON
       StringBuilder log = new StringBuilder();
       log.append("{");

       log.append("\"timestamp\": \"").append(LocalDateTime.now().format(DATE_TIME_FORMATTER)).append("\", ");

       String sig = jp.getSignature().toString();
       String[] parts = sig.split(" ", 2);
       String returnType = parts[0];
       String methodAndParams = parts.length > 1 ? parts[1] : "";

       log.append("\"method\": \"").append(methodAndParams).append("\", ");

       Object[] args = jp.getArgs();
       if (args.length > 0) {
           log.append("\"pessoa\": ");
           log.append(args[0] != null ? args[0].toString() : "null");
           log.append(", ");
       }

       log.append("\"responseType\": \"").append(returnType).append("\", ");
       log.append("\"response\": \"").append(result != null ? result.toString() : "None").append("\"");

       log.append("}");

       logger.info(log.toString());
   }
}
