package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class UserIdUtil {
  private static AtomicInteger userId = new AtomicInteger(1);

  public static int getUserId() {
    int i = userId.incrementAndGet();
    return i;
  }
}
