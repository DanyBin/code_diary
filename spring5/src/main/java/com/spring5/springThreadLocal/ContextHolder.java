package com.spring5.springThreadLocal;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @ClassName ContextHolder
 * @Author bin
 * @Date 2021/4/1 下午4:38
 * @Decr TODO
 *  用于存放Request中的信息
 *  Spring 自带的 RequestContextHolder ，它背后的原理也是 ThreadLocal，不过它总会被更底层的 Servlet 的 Filter 清理掉，因此不存在泄露的问题。
 *  https://blog.csdn.net/Erictju/article/details/79989690
 * @Link TODO
 **/
public class ContextHolder {

    private static final String SECURITY_CONTEXT_ATTRIBUTES = "SECURITY_CONTEXT";

    /**
     * 可以是解析request之后的上下文信息
     * @param context
     */
    public static void setContext(Object context){
        RequestContextHolder.getRequestAttributes().setAttribute(SECURITY_CONTEXT_ATTRIBUTES,
                context,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static  Object get() {
        return RequestContextHolder.getRequestAttributes().getAttribute(SECURITY_CONTEXT_ATTRIBUTES,RequestAttributes.SCOPE_REQUEST);
    }
}
