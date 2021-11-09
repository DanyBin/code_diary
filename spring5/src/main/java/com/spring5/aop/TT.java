package com.spring5.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.reflect.Method;

public class TT extends StaticMethodMatcherPointcut implements Serializable {
  @Override
  public boolean matches(Method method, Class<?> aClass) {
    return false;
  }
}
