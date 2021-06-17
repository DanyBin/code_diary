import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorFunction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static sun.tools.java.Constants.NE;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2021/4/12 下午2:38
 * @Decr TODO
 * @Link TODO
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        double pctr = 0.58;
        double pcxr = 0.98;
        Map<String,Object> map = new HashMap();
        map.put("pctr",pctr);
        map.put("pcxr",pctr);
        map.put("t",0.1);

        String exp = "pctr * t";
        String bol = "pctr > t";

        String  expt = "pptestt *-1343442";
        Expression expression = AviatorEvaluator.compile(exp,true);
        Expression expression1 = AviatorEvaluator.compile(bol,true);
        Expression compile = AviatorEvaluator.compile(expt);

        Object execute = expression.execute(map);
        System.out.println(execute);
        System.out.println(expression1.execute(map));

        NE
    }

}
