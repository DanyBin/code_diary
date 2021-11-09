package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Final {

  private final FinalDomain finalDomain;

  public Final(FinalDomain finalDomain) {
    this.finalDomain = finalDomain;
  }

  public static void main(String[] args) {
    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 20;i ++) {

      executorService.submit(new Runnable() {
        @Override
        public void run() {
          final FinalDomain finalDomain = new FinalDomain();
          final Final aFinal = new Final(finalDomain);
        }
      });
    }
  }

  public static class FinalDomain {
      private String str;
  }
}
