package enumtype.method;

import java.text.DateFormat;
import java.util.Date;

/**
 * 常量相关方法.
 *      通过enum实例，调用的其方法，也称为 表驱动的代码（table-driven code）
 */
public enum ConstantSpecificMethod {

    DATE_TIME {
        @Override
        String getInfo(){
            return DateFormat.getDateInstance().format(new Date());
        }
    },

    CLASSPATH {
        @Override
        String getInfo(){
          return System.getenv("CLASSPATH");
      }
    },

    VERSION {
        @Override
        String getInfo(){
            return System.getProperty("java.version");
        }
    };
    abstract String getInfo();

    public static void main(String[] args) {
        for(ConstantSpecificMethod csm : values()){
            System.out.println(csm.getInfo());
        }
    }
}
