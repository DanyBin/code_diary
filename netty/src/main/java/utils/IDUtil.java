package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDUtil {
  private static AtomicInteger id = new AtomicInteger(10);

  public static int getId() {
    int i = id.incrementAndGet();
    return i;
  }
}
