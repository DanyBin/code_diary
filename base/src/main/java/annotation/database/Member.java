package annotation.database;

import io.file.ProccessFiles;

/**
 * @ClassName Member
 * @Author bin
 * @Date 2020/8/6 上午11:56
 * @Decr TODO
 * @Link TODO
 **/
@DBTable(name = "Member")
public class Member  {

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }


    public static int getMemberCount() {
        return memberCount;
    }

    //设置列.默认使用注解的值
    @SQLString(30) String firstName;
    @SQLString(50) String lastName;
    @SQLInteger Integer age;
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;


    @Override
    public String toString() {
        return handle;
    }
}
