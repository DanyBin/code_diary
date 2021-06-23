package com.spring5.exception;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExceptionTest {

  private ExceptionTest() {
    throw new UnsupportedOperationException();
  }
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    while (true) {
      executorService.submit(() -> {
        try {
          Proxy proxy = new Proxy();
          Method method = Proxy.class.getDeclaredMethod("method", null);
          method.invoke(proxy,null);
        } catch (Exception e) {
          log.error("异常",e);
          log.error("异常",e);
          log.error("异常",e);
          log.error("异常",e);
          log.error("异常",e);
        }
      });
    }
  }

  static class Proxy{
    public void pmethod(){
      System.out.println("Proxy.pmethod 代理成功");
    }
  }
}


