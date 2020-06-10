package immutable;

import javax.annotation.concurrent.Immutable;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @ClassName OneValueCache
 * @Author bin
 * @Date 2020/4/2 上午11:20
 * @Decr TODO
 *      不可变对象
 * @Link TODO
 **/
@Immutable
public final class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactory;

    public OneValueCache( BigInteger lastNumber,BigInteger[] lastFactory){
        this.lastNumber = lastNumber;
        this.lastFactory = Arrays.copyOf(lastFactory,lastFactory.length);
    }

    public BigInteger[] getLastFactory(BigInteger integer){
        if(lastNumber == null || !lastNumber.equals(integer)){
            return null;
        }else {
            return Arrays.copyOf(lastFactory,lastFactory.length);
        }
    }
}
