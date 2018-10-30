package com.phoenix.testspringboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class GirlAspect  {

    // 使用spring的log4j
    private final static Logger log = LoggerFactory.getLogger(GirlAspect.class);

//    /**
//     * 方式一，直接在日志方法上用@before注解标识，但是如果每个方法都要有before、after，则需要写2遍或以上，太麻烦，所以推荐第二种
//     */
//    @Before("execution(public * com.phoenix.testspringboot.controller.GirlController.*(..))")
//    public void logBefore(){
//        log.info("这是方法调用前的日志");
//    }


    /**
     * 方式二 0 写一个公用的切面表达式，然后在每个切面方法上调用该公用方法
     */
    @Pointcut("execution(public * com.phoenix.testspringboot.controller.GirlController.*(..))")
    public void log(){
    }

    /**
     * 方法二 1 前置日志 调用公用切面表达式
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        log.info("这是方法调用前的日志");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求数据log中，用大括号{}可以在记录日志时，把后面传递的参数传到前面的日志参数名后面，结果如url=http://localhost:8080/index的形式
        // url
        log.info("url={}", request.getRequestURL());

        // method
        log.info("method={}", request.getMethod());

        // ip
        log.info("ip={}", request.getRemoteAddr());

        // 类名 + 方法名
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 参数
        log.info("args={}", joinPoint.getArgs());
    }

    /**
     * 方法二 2 后置日志 调用公用切面表达式
     */
    @After("log()")
    public void doAfter(){
        log.info("这是方法调用后的日志");
    }

    /**
     * 方法二 3 方法执行完，返回后的日志 调用公用切面表达式
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterRetuning(Object object){
        log.info("这是方法调用并返回后的日志");

        // 记录返回数据(参数object就是方法返回的值，为com.phoenix.testspringboot.pojo.Girl@1058976e格式，如果需要详情，可以为Girl类添加toString方法)
//        log.info("response={}", object.toString());
    }


}
