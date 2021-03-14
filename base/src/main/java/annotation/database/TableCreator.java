package annotation.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TableCreator
 * @Author bin
 * @Date 2020/8/7 下午2:46
 * @Decr TODO
 *      注解处理器
 * @Link TODO
 **/
public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cl = Class.forName("annotation.database.Member");
        DBTable dbTable =cl.getAnnotation(DBTable.class);

        if(dbTable == null) {
            System.out.println("没有DBTable注解");
        }

        String tableName = dbTable.name();
        if(tableName.length() < 1) {
            tableName = cl.getName().toUpperCase();
        }
        //获取列信息
        List<String> columnDefs = new ArrayList<String>();
        for(Field field : cl.getDeclaredFields()) {
            String columnName = null;
            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length < 1) {
                continue;
            }
            if(anns[0] instanceof SQLInteger) {
                 SQLInteger sInt = (SQLInteger) anns[0];

                 if (sInt.name().length() < 1) {
                     columnName = field.getName().toUpperCase();
                 }else {
                     columnName = sInt.name();
                 }
                 columnDefs.add(columnName + " INT " + getConstraints(sInt.constraints()));
            }

            if(anns[0] instanceof SQLString) {
                SQLString sString = (SQLString)anns[0];
                if(sString.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                }else {
                    columnName = sString.name();
                }
                columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
            }

        }

        StringBuilder createCommand = new StringBuilder("Create Table " + tableName + " (");
        for (String columnDef : columnDefs) {
            createCommand.append("\n " + columnDef + ",");
        }

        String tableCreate = createCommand.substring(0,createCommand.length() -1 ) + " );" ;
        System.out.println(tableCreate);
    }

    private static String getConstraints(Constraints con) {
        String constrains = "";
        if(!con.allowNull()) {
            constrains += " NOT NULL";
        }

        if(con.primaryKey()) {
            constrains += " PRIMARY KEY";
        }

        if(con.unique()) {
            constrains += " UNIQUE";
        }
        return constrains;
    }
}
