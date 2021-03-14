package annotation;

import java.util.List;

/**
 * @ClassName UseCaseable
 * @Author bin
 * @Date 2020/8/6 上午11:17
 * @Decr TODO
 * @Link TODO
 **/
public class UseCaseable {
    @UseCase(id =47,desc = "111")
    public boolean validatePW(String password){
        return password.matches("\\.*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuffer(password).reverse().toString();
    }

    @UseCase(id = 49,desc = "222")
    public boolean checkNewPW(List<String> prevPW,String password) {
        return !prevPW.contains(password);
    }
}
