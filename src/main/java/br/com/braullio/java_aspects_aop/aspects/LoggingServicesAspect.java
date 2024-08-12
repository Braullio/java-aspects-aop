package br.com.braullio.java_aspects_aop.aspects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingServicesAspect {

    private static final String LINE_SEPARATOR = "-------------------------------------------------------------------------------------------------------------------------";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // @Before("execution(* br.com.braullio.java_aspects_aop.services..*(..))")
    // public void logBefore(JoinPoint jp) {
    //     StringBuilder log = new StringBuilder();

    //     log.append(LINE_SEPARATOR).append("\n")
    //         .append("Start : ").append(jp.getSignature()).append("\n")
    //         .append("Timestamp: ").append(LocalDateTime.now().format(DATE_TIME_FORMATTER)).append("\n");

    //     Object[] args = jp.getArgs();
    //     if (args.length > 0) {
    //         log.append("Params: ").append("\n");

    //         for (Object arg : args) {
    //             log.append("- ").append(arg != null ? arg : "null");
    //         }
    //     } else {
    //         log.append("Parameters Value: None\n");
    //     }

    //     log.append("\n").append(LINE_SEPARATOR);
    //     System.out.println(log);
    // }

    @AfterReturning(pointcut = "execution(* br.com.braullio.java_aspects_aop.services..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
        StringBuilder log = new StringBuilder();

        log.append(LINE_SEPARATOR).append("\n")
            .append("Execution: ").append(jp.getSignature()).append("\n")
            .append("Timestamp: ").append(LocalDateTime.now().format(DATE_TIME_FORMATTER)).append("\n");

        Object[] args = jp.getArgs();
        if (args.length > 0) {
            log.append("Params: ").append("\n");

            for (Object arg : args) {
                log.append("- ").append(arg != null ? arg : "null");
            }
        } else {
            log.append("Parameters Value: None\n");
        }

        if (result != null) {
            log.append("\nReturn: ").append(result).append("\n");
        } else {
            log.append("\nReturn: None\n");
        }

        log.append(LINE_SEPARATOR);
        System.out.println(log);
    }
}
