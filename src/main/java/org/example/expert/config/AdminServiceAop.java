package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Aspect
@Component
@RequiredArgsConstructor
public class AdminServiceAop {

    private final HttpServletRequest request;
    private static final Logger logger = Logger.getLogger(AdminServiceAop.class.getName());

    @Around("@annotation(org.example.expert.config.TraceLog)")
    public Object adminServiceLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        // 요청 시간
        String requestTime = now.format(formatter);
        // 요청 url
        String requestUrl = new String(request.getRequestURL());
        // 요청 id
        Long requestUserId =  (Long) request.getAttribute("userId");

        // requestBody는 어떻게 처리해야 될지 모르겠음
        logger.info("requestTime : " + "{" + requestTime + "}");

        Object result = joinPoint.proceed();

        // responseBody는 어떻게 처리해야 될지 모르겠음

        if (result == null) {
            return "No Response Body";
        }


        return result;
    }

}
