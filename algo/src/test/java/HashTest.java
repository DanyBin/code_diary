import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashTest {
  public static double hash(int seed,String hashKey,Double w, Double b) {
    final long hashCode = Hashing.murmur3_128(seed)
        .hashString(hashKey, StandardCharsets.UTF_8)
        .asLong();
    double normalizeHashCode = Math.abs(1.0f * (hashCode % 10000) / 10000);
    return w * normalizeHashCode + b;
  }
  private static Double hash2( String hashKey,Double w, Double b) {
    if (null != b && null != w) {
      long hashCode = Hashing.murmur3_128()
          .hashString(hashKey, StandardCharsets.UTF_8)
          .asLong();

      double normalizeHashCode = Math.abs(1.0f * (hashCode % 10000) / 10000);
      return w * normalizeHashCode + b;
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    String hashKey = "00000000000001679E26B65FD4DDF8FC7043D280E1220A160391373982887594"+16;
    String hashKey2 = "00000000000001679E26B65FD4DDF8FC7043D280E1220A160391373982887596"+16;
    System.out.println(hash(1,hashKey,50d,35d));
    System.out.println(hash(2,hashKey,50d,35d));
    System.out.println(hash2(hashKey,50d,35d));


    System.out.println(hash(1,hashKey2,50d,35d));
    System.out.println(hash(2,hashKey2,50d,35d));
    System.out.println(hash2(hashKey2,50d,35d));
  }
}
