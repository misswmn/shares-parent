package com.shares.core.service.base;

import com.shares.common.util.JsonUtils;
import com.shares.core.service.exception.ResponseEnum;
import com.shares.core.service.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/22
 * @description
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.shares.core.service.*.*(..))")
    private void monitor() {
    }

    @Pointcut("execution(* com.shares.core.controller.*.*(..,org.springframework.validation.BindingResult))")
    private void controllerMonitor() {
    }

    @AfterThrowing(pointcut = "monitor()", throwing = "ex")
    private void beforeService(JoinPoint joinPoint, Exception ex) {
        print(joinPoint, ex);
    }

    @Before("controllerMonitor()")
    public void afterValid(JoinPoint point) {
        String name = point.getSignature().getDeclaringType().getSimpleName();
        String method = point.getSignature().getName();
        Object[] args = point.getArgs();
        BindingResult result;
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                result = (BindingResult) arg;
                if (result.hasErrors()) {
                    List<String> errorMessage = result.getAllErrors().stream().map(oe -> oe.getDefaultMessage()).collect(Collectors.toList());
                    LOGGER.error(MessageFormat.format("{0}.{1} => {2}", name, method, errorMessage));
                    throw new ServiceException(ResponseEnum.ILLEGAL_PARAM, StringUtils.join(errorMessage, ","));
                }
            }
        }
    }

    private void print(JoinPoint joinPoint, Exception ex) {
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> errorSet = e.getConstraintViolations();
            List<String> errorList = errorSet.stream().map(exc -> exc.getMessage()).collect(Collectors.toList());
            throw new IllegalArgumentException(StringUtils.join(errorList, ","));
        } else {
            String name = joinPoint.getSignature().getDeclaringType().getSimpleName();
            String method = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String key = MessageFormat.format("{0}.{1} >>>>> {2}", name, method, JsonUtils.objectToJson(args));
            LOGGER.error(key, ex);
        }
    }
}
